package com.baiyan.ddd.application.ability.user;

import com.baiyan.ddd.application.ability.share.BaseAbility;
import com.baiyan.ddd.application.ability.user.cmd.CreateUserAbilityCommand;
import com.baiyan.ddd.application.query.RoleQueryApplicationService;
import com.baiyan.ddd.application.query.UserQueryApplicationService;
import com.baiyan.ddd.application.query.model.role.dto.RoleDTO;
import com.baiyan.ddd.base.model.result.Result;
import com.baiyan.ddd.base.util.ValidationUtil;
import com.baiyan.ddd.domain.aggregate.user.event.UserCreateEvent;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import com.baiyan.ddd.domain.aggregate.user.repository.UserRepository;
import com.baiyan.ddd.domain.share.event.DomainEventPublisher;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 创建用户能力
 *
 * @author baiyan
 */
@Service
public class UserCreateAbility extends BaseAbility<CreateUserAbilityCommand, Void> {

    @Autowired
    UserQueryApplicationService userQueryApplicationService;

    @Autowired
    RoleQueryApplicationService roleQueryApplicationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DomainEventPublisher domainEventPublisher;

    @Override
    public void checkHandler(CreateUserAbilityCommand command) {
        //校验用户名不存在
        ValidationUtil.isTrue(Objects.isNull(userQueryApplicationService.detail(command.getUserName())),"user.user.name.is.exist");
        //校验角色存在
        List<RoleDTO> roles = roleQueryApplicationService.list(command.getRoles());
        ValidationUtil.isTrue(CollectionUtils.isNotEmpty(roles) &&
                        Objects.equals(roles.size(),command.getRoles().size()),
                "user.role.is.not.exist");
    }

    @Override
    public Result<Void> checkIdempotent(CreateUserAbilityCommand command) {

        //在这里进行幂等处理判断

        return Result.success(null);
    }

    @Override
    public Result<Void> execute(CreateUserAbilityCommand command) {

        //工厂创建用户
        User user = command.toUser(command);

        //存储用户
        User save = userRepository.save(user);

        //发布用户新建的领域事件
        domainEventPublisher.publish(new UserCreateEvent(save));

        return Result.success(null);
    }

}
