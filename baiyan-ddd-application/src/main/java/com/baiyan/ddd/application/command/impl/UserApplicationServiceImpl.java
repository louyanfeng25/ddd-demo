package com.baiyan.ddd.application.command.impl;

import com.baiyan.ddd.application.command.UserApplicationService;
import com.baiyan.ddd.application.command.cmd.user.CreateUserCommand;
import com.baiyan.ddd.application.command.cmd.user.UpdateUserCommand;
import com.baiyan.ddd.application.query.UserQueryApplicationService;
import com.baiyan.ddd.application.query.model.user.dto.UserDTO;
import com.baiyan.ddd.base.util.ValidationUtil;
import com.baiyan.ddd.domain.aggregate.user.event.UserCreateEvent;
import com.baiyan.ddd.domain.aggregate.user.event.UserDeleteEvent;
import com.baiyan.ddd.domain.aggregate.user.event.UserUpdateEvent;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import com.baiyan.ddd.domain.aggregate.user.repository.UserRepository;
import com.baiyan.ddd.domain.share.event.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 用户应用服务
 *
 * 业务逻辑编排，仅对业务用例做方法的编排
 *
 * @author baiyan
 */
@Slf4j
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserQueryApplicationService userQueryApplicationService;

    @Autowired
    DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateUserCommand command){
        //这里如果校验逻辑比较多，可以单独抽取一个UserValidationUtil进行参数校验
        //也可以在applicationService中把所需要校验的参数全部查询完，然后把校验逻辑卸载domain里面
        ValidationUtil.isTrue(Objects.isNull(userQueryApplicationService.detail(command.getUserName())),"user.user.name.is.exist");
        //工厂创建用户
        User user = command.toUser(command);
        //存储用户
        User save = userRepository.save(user);
        //发布用户新建的领域事件
        domainEventPublisher.publish(new UserCreateEvent(save));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateUserCommand command){
        //先校验用户是否存在【应用服务仅允许此种判断，抛出错误情况，即为参数校验，不允许实际业务逻辑处理】
        ValidationUtil.isTrue(Objects.nonNull(userRepository.byId(command.getUserId())),"user.is.not.exist");
        User user = command.toUser(command);
        //校验用户名
        UserDTO existUser = userQueryApplicationService.detail(command.getUserName());
        ValidationUtil.isTrue(Objects.isNull(existUser) || Objects.equals(existUser.getId(),command.getUserId()),"user.user.name.is.exist");
        //存储用户
        User save = userRepository.save(user);
        //发布用户修改的领域事件
        domainEventPublisher.publish(new UserUpdateEvent(save));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        ValidationUtil.isTrue(Objects.nonNull(userRepository.byId(id)),"user.is.not.exist");
        //根据用户id删除用户聚合
        userRepository.delete(id);
        //发布用户删除领域事件
        domainEventPublisher.publishAndSave(new UserDeleteEvent(id));
    }

}
