package com.baiyan.ddd.application.command.cmd.user;

import com.baiyan.ddd.base.model.ddd.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 修改用户指令
 * @author baiyan
 */
@Data
public class UpdateUserCommand implements Command {

    @NotNull(message = "{user.id.is.null}")
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "{user.userName.is.blank}")
    private String userName;

}
