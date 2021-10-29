package com.baiyan.ddd.biz.user.application.factory;

import com.baiyan.ddd.biz.user.application.model.command.CreateUserCommand;
import com.baiyan.ddd.biz.user.application.model.command.UpdateUserCommand;
import com.baiyan.ddd.biz.user.domain.model.User;

/**
 * 用户聚合创建工厂
 *
 * 复杂逻辑使用工厂与新建聚合，且UserFactory可以被申明为一个bean，去依赖应用服务
 * 或者领域服务，本质上工厂只是一个工具类，用来生产聚合，逻辑合理
 *
 * 从上面的角度来看，工厂本身并不是领域的一部分，它只是用来创造聚合
 *
 * 当然有的人也把这个认为是领域的一部分，把factory这个包放在domain的包下，然后使用
 * 与lombok类似的builder方式对新建聚合做一层工具类的包装。我觉得太繁琐，为了形式而形式
 * 没必要，大多数场景下直接通过command的toDomain方法即可得到聚合，少数复杂聚合才需要工厂包装
 *
 * @author baiyan
 */
public class UserFactory {

    /**
     * 新建用户聚合
     *
     * 这里的操作是，聚合根本身开放了比较建议的聚合根构造方法，最简易的用户
     * 但是复杂用户需要逐个方法去进行bind逻辑。相当于在原有构造方法基础上，为
     * 聚合增加了更多的业务属性
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
