package com.baiyan.ddd.application.query;

import com.baiyan.ddd.application.query.model.role.dto.RoleDTO;
import com.baiyan.ddd.base.model.ddd.QueryApplicationService;

import java.util.List;

/**
 * 用户查询应用服务
 *
 * 复杂逻辑在此组合，简单逻辑可直接CQRS,直接查询仓储层进行数据聚合
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
