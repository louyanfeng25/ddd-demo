package com.baiyan.ddd.biz.user.domain.repository;

import com.baiyan.ddd.base.model.ddd.Repository;
import com.baiyan.ddd.biz.user.domain.model.User;

/**
 * 用户仓储接口
 *
 * @author baiyan
 */
public interface UserRepository extends Repository<User,Long> {
}
