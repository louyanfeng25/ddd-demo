package com.baiyan.ddd.infrastructure.db.mapper;

import com.baiyan.ddd.base.model.query.KeywordQuery;
import com.baiyan.ddd.base.model.result.Page;
import com.baiyan.ddd.infrastructure.db.model.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息mapper
 *
 * @author baiyan
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    /**
     * 用户信息分页查询
     *
     * @param query
     * @return
     */
    Page<UserPO> userPage(KeywordQuery query);
}
