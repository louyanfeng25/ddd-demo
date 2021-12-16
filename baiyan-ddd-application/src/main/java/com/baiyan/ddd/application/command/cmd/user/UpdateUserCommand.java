package com.baiyan.ddd.application.command.cmd.user;

import com.baiyan.ddd.base.model.ddd.Command;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 修改用户指令
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateUserCommand extends BaseUserCommand implements Command {

    @NotNull(message = "{user.id.is.null}")
    private Long userId;

    /**
     * 构建出需要修改的用户
     *
     * @param command
     * @return
     */
    public User toUser(UpdateUserCommand command){
        User user = User.builder()
                .id(command.getUserId())
                .userName(command.getUserName())
                .realName(command.getRealName())
                .phone(command.getPhone())
                .build();
        user.bindUnit(command.getUnitId());
        user.bindRole(command.getRoles());
        user.bindAddress(command.getProvince(),command.getCity(),command.getCounty());
        return user;
    }
}
