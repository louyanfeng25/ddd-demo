package com.baiyan.ddd.application.command.impl;

import com.baiyan.ddd.application.ability.user.UserCreateAbility;
import com.baiyan.ddd.application.ability.user.cmd.CreateUserAbilityCommand;
import com.baiyan.ddd.application.command.UserApplicationService;
import com.baiyan.ddd.application.command.cmd.user.UpdateUserCommand;
import com.baiyan.ddd.base.util.ValidationUtil;
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
 * @author baiyan
 */
@Slf4j
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    @Autowired
    UserCreateAbility userCreateAbility;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateUserAbilityCommand command){
        userCreateAbility.executeAbility(command);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserName(UpdateUserCommand command){
        //【应用服务仅允许此种判断，抛出错误情况，即为参数校验，不允许实际业务逻辑处理】
        // 前置校验逻辑很长或者通用率高的情况，可以考虑抽取一个UserValidationUtil统一管理前置的业务校验

        //先校验用户是否存在
        User user = userRepository.byId(command.getUserId());
        ValidationUtil.isTrue(Objects.nonNull(user),"user.is.not.exist");

        //修改用户名
        User existUser = userRepository.byUserName(command.getUserName());
        user.bindUserName(command.getUserName(),existUser);

        //执行用户修改相关业务逻辑
        user.printUpdate();

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
