package com.baiyan.ddd.core.user.command;

import com.baiyan.ddd.base.model.ddd.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 新建用户命令
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateUserCommand extends BaseUserCommand implements Command {

    /**
     * 用户密码
     */
    @NotBlank(message = "{user.password.is.blank}")
    private String password;

}
