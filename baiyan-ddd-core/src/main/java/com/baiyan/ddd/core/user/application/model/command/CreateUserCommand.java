package com.baiyan.ddd.core.user.application.model.command;

import com.baiyan.ddd.base.model.ddd.Command;
import com.baiyan.ddd.core.user.domain.model.User;
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

    /**
     * 将command转换成聚合根
     *
     * 逻辑简单，只是做一些字段的映射则在command里面直接转化返回给应用服务层使用即可。
     *
     * 如果逻辑复杂，command参数进来需要做一些比较复杂的逻辑处理，则使用工厂
     * @see com.baiyan.ddd.core.user.application.factory.UserFactory
     *
     *
     * @param command
     * @return
     */
    public User toUser(CreateUserCommand command){
        User user = new User(command.getUserName(), command.getRealName(), command.getPhone(), command.getPassword());
        user.bindAddress(command.getProvince(),command.getCity(),command.getCounty());
        user.bindRoleByRoleId(command.getRoles());
        return user;
    }
}
