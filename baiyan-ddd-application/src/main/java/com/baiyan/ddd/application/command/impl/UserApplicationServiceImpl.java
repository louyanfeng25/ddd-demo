package com.baiyan.ddd.application.command.impl;

import com.baiyan.ddd.application.ability.user.UserCreateAbility;
import com.baiyan.ddd.application.ability.user.cmd.CreateUserAbilityCommand;
import com.baiyan.ddd.application.command.UserApplicationService;
import com.baiyan.ddd.application.command.cmd.user.UpdateUserCommand;
import com.baiyan.ddd.application.query.RoleQueryApplicationService;
import com.baiyan.ddd.application.query.UserQueryApplicationService;
import com.baiyan.ddd.application.query.model.role.dto.RoleDTO;
import com.baiyan.ddd.application.query.model.user.dto.UserDTO;
import com.baiyan.ddd.base.util.ValidationUtil;
import com.baiyan.ddd.domain.aggregate.user.event.UserDeleteEvent;
import com.baiyan.ddd.domain.aggregate.user.event.UserUpdateEvent;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import com.baiyan.ddd.domain.aggregate.user.repository.UserRepository;
import com.baiyan.ddd.domain.share.event.DomainEventPublisher;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    UserQueryApplicationService userQueryApplicationService;

    @Autowired
    RoleQueryApplicationService roleQueryApplicationService;

    @Autowired
    DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateUserAbilityCommand command){
        userCreateAbility.executeAbility(command);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateUserCommand command){
        //【应用服务仅允许此种判断，抛出错误情况，即为参数校验，不允许实际业务逻辑处理】
        // 前置校验逻辑很长或者通用率高的情况，可以考虑抽取一个UserValidationUtil统一管理前置的业务校验

        //先校验用户是否存在
        ValidationUtil.isTrue(Objects.nonNull(userRepository.byId(command.getUserId())),"user.is.not.exist");
        //校验传入的角色是否存在
        List<RoleDTO> roles = roleQueryApplicationService.list(command.getRoles());
        ValidationUtil.isTrue(CollectionUtils.isNotEmpty(roles) &&
                Objects.equals(roles.size(),command.getRoles().size()),
                "user.role.is.not.exist");
        //校验用户名
        UserDTO existUser = userQueryApplicationService.detail(command.getUserName());
        ValidationUtil.isTrue(Objects.isNull(existUser) || Objects.equals(existUser.getId(),command.getUserId()),"user.user.name.is.exist");


        User user = command.toUser(command);
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
