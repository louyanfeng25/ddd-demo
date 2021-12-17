package com.baiyan.ddd.infrastructure.db.converter;

import com.baiyan.ddd.domain.aggregate.user.model.Role;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import com.baiyan.ddd.infrastructure.db.model.UserPO;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

/**
 * 用户转换器
 *
 * @author baiyan
 */
public class UserConverter {

    /**
     * 数据模型转领域模型
     *
     * @param po 用户数据模型
     * @return 用户领域模型
     */
    public static User deserialize(UserPO po) {
        User user = User.builder()
                .id(po.getId())
                .userName(po.getUserName())
                .realName(po.getRealName())
                .phone(po.getPhone())
                .password(po.getPassword())
                .gmtCreate(po.getGmtCreate())
                .gmtModified(po.getGmtModified())
                .build();
        user.bindUnit(po.getUnitId());
        user.bindRole(po.getRoleIds());
        user.bindAddress(po.getProvince(),po.getCity(),po.getCounty());
        return user;
    }

    /**
     * 领域模型转数据模型
     *
     * @param user 用户领域模型
     * @return 用户数据模型
     */
    public static UserPO serialize(User user){
        UserPO po = new UserPO();
        BeanUtils.copyProperties(user,po);
        po.setCity(user.getAddress().getCity());
        po.setCounty(user.getAddress().getCounty());
        po.setProvince(user.getAddress().getProvince());
        po.setUnitId(user.getUnit().getId());
        //设置角色id
        String roleIds = user.getRoles().stream().map(Role::getId).map(String::valueOf).collect(Collectors.joining(","));
        po.setRoleIds(roleIds);
        return po;
    }

}
