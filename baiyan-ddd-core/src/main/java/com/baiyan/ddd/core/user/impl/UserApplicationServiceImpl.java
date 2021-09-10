package com.baiyan.ddd.core.user.impl;

import com.baiyan.ddd.base.util.ValidationUtil;
import com.baiyan.ddd.core.infrastructure.common.event.DomainEventPublisher;
import com.baiyan.ddd.core.user.User;
import com.baiyan.ddd.core.user.UserApplicationService;
import com.baiyan.ddd.core.user.UserDomainService;
import com.baiyan.ddd.core.user.UserRepository;
import com.baiyan.ddd.core.user.command.CreateUserCommand;
import com.baiyan.ddd.core.user.command.UpdateUserCommand;
import com.baiyan.ddd.core.user.event.UserCreateEvent;
import com.baiyan.ddd.core.user.event.UserDeleteEvent;
import com.baiyan.ddd.core.user.event.UserUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    DomainEventPublisher domainEventPublisher;

    @Autowired
    UserDomainService userDomainService;

    @Override
    public void create(CreateUserCommand command){
        //工厂创建用户
        User user = new User(command);
        //关联单位单位信息
        userDomainService.associatedUnit(command.getUnitId(),user);
        //存储用户
        User save = userRepository.save(user);
        //发布用户新建的领域事件
        domainEventPublisher.publishEvent(new UserCreateEvent(save));
    }

    @Override
    public void update(UpdateUserCommand command){
        //先校验用户是否存在【应用服务仅允许此种判断，抛出错误情况，即为参数校验，不允许实际业务逻辑处理】
        ValidationUtil.isTrue(Objects.nonNull(userRepository.byId(command.getUserId())),"user.is.not.exist");
        //工厂创建用户
        User user = new User(command);
        //设置单位信息
        userDomainService.associatedUnit(command.getUnitId(),user);
        //存储用户
        User save = userRepository.save(user);
        //发布用户修改的领域事件
        domainEventPublisher.publishEvent(new UserUpdateEvent(save));
    }

    @Override
    public void delete(Long id){
        //根据用户id删除用户聚合
        userRepository.delete(id);
        //发布用户删除领域事件
        domainEventPublisher.publishEvent(new UserDeleteEvent(id));
    }


    @Override
    public User select(Long id){
        return userRepository.byId(id);
    }

}
