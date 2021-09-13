package com.baiyan.ddd.core.user.application.model.command;

import com.baiyan.ddd.base.model.ddd.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 修改用户命令
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateUserCommand extends BaseUserCommand implements Command {

    @NotNull(message = "{user.id.is.null}")
    private Long userId;
}
