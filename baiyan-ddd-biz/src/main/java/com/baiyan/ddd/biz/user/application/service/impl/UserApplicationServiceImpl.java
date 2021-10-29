package com.baiyan.ddd.biz.user.application.service.impl;

import com.baiyan.ddd.base.util.ValidationUtil;
import com.baiyan.ddd.biz.user.application.factory.UserFactory;
import com.baiyan.ddd.biz.user.application.model.command.CreateUserCommand;
import com.baiyan.ddd.biz.user.application.model.command.UpdateUserCommand;
import com.baiyan.ddd.biz.user.application.service.UserApplicationService;
import com.baiyan.ddd.biz.user.domain.event.UserCreateEvent;
import com.baiyan.ddd.biz.user.domain.event.UserDeleteEvent;
import com.baiyan.ddd.biz.user.domain.event.UserUpdateEvent;
import com.baiyan.ddd.biz.user.domain.model.User;
import com.baiyan.ddd.biz.user.domain.repository.UserRepository;
import com.baiyan.ddd.biz.user.domain.service.UserDomainService;
import common.event.DomainEventPublisher;
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
    DomainEventPublisher domainEventPublisher;

    @Autowired
    UserDomainService userDomainService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateUserCommand command){
        //工厂创建用户
        User user = UserFactory.createUser(command);
        //关联单位单位信息
        userDomainService.associatedUnit(command.getUnitId(),user);
        //存储用户
        User save = userRepository.save(user);
        //发布用户新建的领域事件
        domainEventPublisher.publishEvent(new UserCreateEvent(save));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateUserCommand command){
        //先校验用户是否存在【应用服务仅允许此种判断，抛出错误情况，即为参数校验，不允许实际业务逻辑处理】
        ValidationUtil.isTrue(Objects.nonNull(userRepository.byId(command.getUserId())),"user.is.not.exist");
        //工厂创建用户
        User user = UserFactory.updateUser(command);
        //设置单位信息
        userDomainService.associatedUnit(command.getUnitId(),user);
        //存储用户
        User save = userRepository.save(user);
        //发布用户修改的领域事件
        domainEventPublisher.publishEvent(new UserUpdateEvent(save));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        //根据用户id删除用户聚合
        userRepository.delete(id);
        //发布用户删除领域事件
        domainEventPublisher.publishEvent(new UserDeleteEvent(id));
    }

}
