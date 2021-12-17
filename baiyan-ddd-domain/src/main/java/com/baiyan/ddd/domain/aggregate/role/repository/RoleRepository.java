package com.baiyan.ddd.domain.aggregate.role.repository;

import com.baiyan.ddd.base.model.ddd.Repository;
import com.baiyan.ddd.domain.aggregate.role.model.Role;

import java.util.List;

/**
 * 用户仓储接口
 *
 * @author baiyan
 */
public interface RoleRepository extends Repository<Role,Long> {

    /**
     * 根据id列表获取角色列表
     *
     * @param ids
     * @return
     */
    List<Role> listByIds(List<Long> ids);
}
