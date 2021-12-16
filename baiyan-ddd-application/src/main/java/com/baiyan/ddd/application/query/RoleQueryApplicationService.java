package com.baiyan.ddd.application.query;

import com.baiyan.ddd.application.query.model.role.dto.RoleDTO;
import com.baiyan.ddd.base.model.ddd.QueryApplicationService;

import java.util.List;

/**
 * 角色查询应用服务
 *
 * @author baiyan
 */
public interface RoleQueryApplicationService extends QueryApplicationService {

    /**
     * 根据角色id查询信息
     *
     * @param roleIds
     * @return
     */
    List<RoleDTO> list(List<Long> roleIds);

}
