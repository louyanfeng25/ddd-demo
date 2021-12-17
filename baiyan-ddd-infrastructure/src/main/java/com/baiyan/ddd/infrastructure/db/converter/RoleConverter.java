package com.baiyan.ddd.infrastructure.db.converter;

import com.baiyan.ddd.domain.aggregate.role.model.Role;
import com.baiyan.ddd.infrastructure.db.model.RolePO;
import org.springframework.beans.BeanUtils;

/**
 * 角色转换器
 *
 * @author baiyan
 */
public class RoleConverter {

    /**
     * 数据模型转领域模型
     *
     * @param po 角色数据模型
     * @return 角色领域模型
     */
    public static Role deserialize(RolePO po) {
        return Role.builder()
                .id(po.getId())
                .code(po.getCode())
                .name(po.getName())
                .gmtCreate(po.getGmtCreate())
                .gmtModified(po.getGmtModified())
                .build();
    }

    /**
     * 领域模型转数据模型
     *
     * @param role 角色领域模型
     * @return 角色数据模型
     */
    public static RolePO serialize(Role role){
        RolePO po = new RolePO();
        BeanUtils.copyProperties(role,po);
        return po;
    }

}
