package com.baiyan.ddd.biz.user.domain.service;

import com.baiyan.ddd.base.model.ddd.DomainService;
import com.baiyan.ddd.biz.user.domain.model.User;

/**
 * 用户领域服务
 *
 * @author baiyan
 */
public interface UserDomainService extends DomainService {

    /**
     * 设置单位信息
     *
     * 这里仅为了演示逻辑，仅从demo来看，完全可以在application层调用interface获取
     * 单位信息，再调用聚合根逻辑绑定
     *
     * 这里演示application如果引用领域服务，领域服务需要调用其他的领域服务/应用服务/interface时
     * 不能直接调用，需要转接一层interface，防腐领域服务层，不会依赖倒置
     *
     * 在退一步讲，理论上，领域服务的逻辑应该很少，理想状态下就没有领域服务，interface
     * 是为了让大家在迁移过程中或者在开发过程中留的一个小口子，繁琐一步，但是保障了整个系统的
     * 依赖合理性。
     *
     * @param unitId
     * @param user
     */
    void associatedUnit(Long unitId, User user);
}
