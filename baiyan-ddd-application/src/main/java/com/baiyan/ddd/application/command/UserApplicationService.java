package com.baiyan.ddd.application.command;

import com.baiyan.ddd.application.ability.user.cmd.CreateUserAbilityCommand;
import com.baiyan.ddd.application.command.cmd.user.UpdateUserCommand;
import com.baiyan.ddd.base.model.ddd.ApplicationService;

/**
 * 用户应用服务
 *
 * 业务逻辑编排，仅对业务用例做方法的编排
 *
 * @author baiyan
 */
public interface UserApplicationService extends ApplicationService {

    /**
     * 新建用户
     *
     * @param command
     */
    void create(CreateUserAbilityCommand command);

    /**
     * 修改用户用户名
     *
     * @param command
     */
    void updateUserName(UpdateUserCommand command);

    /**
     * 删除用户
     *
     * @param id
     */
    void delete(Long id);

}
