package com.baiyan.ddd.core.user;

import cn.hutool.core.collection.CollUtil;
import com.baiyan.ddd.base.model.ddd.AggregateRoot;
import com.baiyan.ddd.base.model.result.BaseUuidEntity;
import com.baiyan.ddd.core.infrastructure.repository.po.RolePO;
import com.baiyan.ddd.core.infrastructure.repository.po.UserPO;
import com.baiyan.ddd.core.user.command.CreateUserCommand;
import com.baiyan.ddd.core.user.command.UpdateUserCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户聚合根
 *
 * @author baiyan
 */
@Getter
@NoArgsConstructor
public class User extends BaseUuidEntity implements AggregateRoot {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户真实名称
     */
    private String realName;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户地址
     */
    private Address address;

    /**
     * 用户单位
     */
    private Unit unit;

    /**
     * 角色
     */
    private List<Role> roles;

    /**
     * 新建用户
     *
     * @param command 新建用户指令
     */
    public User(CreateUserCommand command){
        this.userName = command.getUserName();
        this.realName = command.getRealName();
        this.phone = command.getPhone();
        this.password = command.getPassword();
        this.setAddress(command.getProvince(),command.getCity(),command.getCounty());
        this.relativeRoleByRoleId(command.getRoles());
    }

    /**
     * 修改用户
     *
     * @param command 修改用户指令
     */
    public User(UpdateUserCommand command){
        this.setId(command.getUserId());
        this.userName = command.getUserName();
        this.realName = command.getRealName();
        this.phone = command.getPhone();
        this.setAddress(command.getProvince(),command.getCity(),command.getCounty());
        this.relativeRoleByRoleId(command.getRoles());
    }

    /**
     * 组装聚合
     *
     * @param userPO
     * @param roles
     */
    public User(UserPO userPO, List<RolePO> roles){
        this.setId(userPO.getId());
        this.setDeleted(userPO.getDeleted());
        this.setGmtCreate(userPO.getGmtCreate());
        this.setGmtModified(userPO.getGmtModified());
        this.userName = userPO.getUserName();
        this.realName = userPO.getRealName();
        this.phone = userPO.getPhone();
        this.password = userPO.getPassword();
        this.setAddress(userPO.getProvince(),userPO.getCity(),userPO.getCounty());
        this.relativeRoleByRolePO(roles);
        this.setUnit(userPO.getUnitId(),userPO.getUnitName());
    }

    /**
     * 根据角色id设置角色信息
     *
     * @param roleIds 角色id
     */
    public void relativeRoleByRoleId(List<Long> roleIds){
        this.roles = roleIds.stream()
                .map(roleId->new Role(roleId,null,null))
                .collect(Collectors.toList());
    }

    /**
     * 设置角色信息
     *
     * @param roles
     */
    public void relativeRoleByRolePO(List<RolePO> roles){
        if(CollUtil.isEmpty(roles)){
            return;
        }
        this.roles = roles.stream()
                .map(e->new Role(e.getId(),e.getCode(),e.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 设置用户地址信息
     *
     * @param province 省
     * @param city 市
     * @param county 区
     */
    public void setAddress(String province,String city,String county){
        this.address = new Address(province,city,county);
    }

    /**
     * 设置用户单位信息
     *
     * @param unitId
     * @param unitName
     */
    public void setUnit(Long unitId,String unitName){
        this.unit = new Unit(unitId,unitName);
    }

}
