package com.baiyan.ddd.biz.user.interfaces;

import com.baiyan.ddd.base.model.ddd.Interface;
import com.baiyan.ddd.biz.user.interfaces.model.UnitDTO;

/**
 * 用户灰度层，领域层防腐
 *
 * 用于协调不同上下文之间的逻辑
 *
 * @author baiyan
 */
public interface UserInterface extends Interface {

    /**
     * 根据单位id获取单位
     *
     * 注意此处如果有多个上下文需要调用同一个rpc接口逻辑，那么从共享内核角度
     * 可以把具体的逻辑实现在share/service包下，可由领域服务或者应用服务直接调用
     *
     * @param unitId
     * @return
     */
    UnitDTO findUnitByUnitId(Long unitId);

}
