package com.baiyan.ddd.domain.aggregate.user.repository;

import com.baiyan.ddd.base.model.ddd.Repository;
import com.baiyan.ddd.domain.aggregate.user.model.User;

/**
 * 用户仓储接口
 *
 * @author baiyan
 */
public interface UserRepository extends Repository<User,Long> {
}
