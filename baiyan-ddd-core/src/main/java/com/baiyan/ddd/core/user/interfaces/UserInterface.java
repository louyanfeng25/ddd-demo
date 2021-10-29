package com.baiyan.ddd.core.user.interfaces;

import com.baiyan.ddd.base.model.ddd.Interface;
import com.baiyan.ddd.core.infrastructure.adapter.model.UnitDTO;

/**
 * 用户灰度层，领域层防腐
 *
 * @author baiyan
 */
public interface UserInterface extends Interface {

    /**
     * 根据单位id获取单位
     *
     * 仅为了演示把逻辑写在这里，interface层本质意义上还是为了协调
     * 本领域与本领域无关的业务场景，防腐只用。
     *
     * @param unitId
     * @return
     */
    UnitDTO findUnitByUnitId(Long unitId);

}
