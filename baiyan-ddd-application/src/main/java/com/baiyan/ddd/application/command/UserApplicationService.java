package com.baiyan.ddd.application.command;

import com.baiyan.ddd.application.command.cmd.user.CreateUserCommand;
import com.baiyan.ddd.application.command.cmd.user.UpdateUserCommand;
import com.baiyan.ddd.base.model.ddd.ApplicationService;

/**
 * 用户应用服务
 *
 * @author baiyan
 */
public interface UserApplicationService extends ApplicationService {

    /**
     * 新建用户
     *
     * @param command
     */
    void create(CreateUserCommand command);

    /**
     * 修改用户
     *
     * @param command
     */
    void update(UpdateUserCommand command);

    /**
     * 删除用户
     *
     * @param id
     */
    void delete(Long id);

}
