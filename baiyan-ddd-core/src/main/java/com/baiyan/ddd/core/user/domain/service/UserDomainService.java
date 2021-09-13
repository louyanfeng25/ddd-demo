package com.baiyan.ddd.core.user.domain.service;

import com.baiyan.ddd.base.model.ddd.DomainService;
import com.baiyan.ddd.core.user.domain.model.User;

/**
 * 用户领域服务
 *
 * @author baiyan
 */
public interface UserDomainService extends DomainService {

    /**
     * 设置单位信息
     *
     * @param unitId
     * @param user
     */
    void associatedUnit(Long unitId, User user);
}
