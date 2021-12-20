package com.baiyan.ddd.domain.aggregate.user.service;

import com.baiyan.ddd.base.model.ddd.DomainService;
import com.baiyan.ddd.domain.aggregate.role.model.Role;
import com.baiyan.ddd.domain.aggregate.user.model.User;

import java.util.List;

/**
 * 用户领域服务
 *
 * @author baiyan
 */
public interface UserDomainService  extends DomainService {

    /**
     * 需要用户角色两个聚合完成用户聚合的原子化逻辑
     *
     * 根据用户关联的角色打印出标签
     *
     * @param user 用户聚合
     * @param roles 角色聚合
     */
    void printTag(User user, List<Role> roles);
}
