package com.baiyan.ddd.core.user.domain.factory;

import com.baiyan.ddd.core.user.application.model.command.CreateUserCommand;
import com.baiyan.ddd.core.user.application.model.command.UpdateUserCommand;
import com.baiyan.ddd.core.user.domain.model.User;

/**
 * 用户聚合创建工厂
 *
 * @author baiyan
 */
public class UserFactory {

    /**
     * 新建用户聚合
     *
     * @param command
     * @return
     */
    public static User createUser(CreateUserCommand command){
        User user = new User(command.getUserName(), command.getRealName(), command.getPhone(), command.getPassword());
        user.bindAddress(command.getProvince(),command.getCity(),command.getCounty());
        user.bindRoleByRoleId(command.getRoles());
        return user;
    }

    /**
     * 修改用户聚合
     *
     * @param command
     * @return
     */
    public static User updateUser(UpdateUserCommand command){
        User user = new User(command.getUserName(), command.getRealName(), command.getPhone(), null);
        user.setId(command.getUserId());
        user.bindAddress(command.getProvince(),command.getCity(),command.getCounty());
        user.bindRoleByRoleId(command.getRoles());
        return user;
    }

}
