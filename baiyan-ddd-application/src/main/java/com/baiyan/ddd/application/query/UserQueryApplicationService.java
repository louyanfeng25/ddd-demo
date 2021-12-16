package com.baiyan.ddd.application.query;

import com.baiyan.ddd.application.query.model.user.dto.UserDTO;
import com.baiyan.ddd.application.query.model.user.dto.UserPageDTO;
import com.baiyan.ddd.base.model.ddd.QueryApplicationService;
import com.baiyan.ddd.base.model.query.KeywordQuery;
import com.baiyan.ddd.base.model.result.Page;

/**
 * 用户查询应用服务
 *
 * @author baiyan
 */
public interface UserQueryApplicationService extends QueryApplicationService {

    /**
     * 用户分页数据查询
     *
     * @param query
     * @return
     */
    Page<UserPageDTO> userPage(KeywordQuery query);

    /**
     * 根据用户名称查询用户详情
     *
     * @param userName
     * @return
     */
    UserDTO detail(String userName);

}
