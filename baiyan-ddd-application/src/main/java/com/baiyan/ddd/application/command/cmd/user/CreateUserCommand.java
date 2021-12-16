package com.baiyan.ddd.application.command.cmd.user;

import com.baiyan.ddd.base.model.ddd.Command;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 新建用户指令
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

    /**
     * 将command转换成聚合根
     *
     * 逻辑简单，只是做一些字段的映射则在command里面直接转化返回给应用服务层使用即可。
     *
     * 如果逻辑复杂，command参数进来需要做一些比较复杂的逻辑处理，则使用工厂类
     *
     * @param command
     * @return
     */
    public User toUser(CreateUserCommand command){
        User user = User.builder()
                .userName(command.getUserName())
                .realName(command.getRealName())
                .phone(command.getPhone())
                .password(command.getPassword())
                .build();
        user.bindUnit(command.getUnitId());
        user.bindRole(command.getRoles());
        user.bindAddress(command.getProvince(),command.getCity(),command.getCounty());
        return user;
    }
}
