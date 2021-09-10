package com.baiyan.ddd.core.user;

import com.baiyan.ddd.base.model.ddd.QueryApplicationService;

/**
 * 用户查询应用服务
 *
 * 复杂逻辑在此组合，简单逻辑可直接CQRS,直接查询仓储层进行数据聚合
 *
 * @author baiyan
 */
public interface UserQueryApplicationService extends QueryApplicationService {

}
