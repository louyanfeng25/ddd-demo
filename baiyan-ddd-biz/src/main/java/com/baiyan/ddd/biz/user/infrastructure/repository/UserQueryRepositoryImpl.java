package com.baiyan.ddd.biz.user.infrastructure.repository;

import com.baiyan.ddd.base.model.query.KeywordQuery;
import com.baiyan.ddd.base.model.result.Page;
import com.baiyan.ddd.biz.user.application.model.dto.UserPageDTO;
import com.baiyan.ddd.biz.user.application.repository.UserQueryRepository;
import com.baiyan.ddd.biz.user.infrastructure.converter.UserConverter;
import com.baiyan.ddd.biz.user.infrastructure.repository.mapper.UserMapper;
import com.baiyan.ddd.biz.user.infrastructure.repository.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * 用户信息查询仓储
 *
 * @author baiyan
 */
@Repository
public class UserQueryRepositoryImpl implements UserQueryRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserPageDTO> userPage(KeywordQuery query){
        Page<UserPO> userPos = userMapper.userPage(query);
        return UserConverter.serializeUserPage(userPos);
    }

}
