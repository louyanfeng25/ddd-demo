package com.baiyan.ddd.core.infrastructure.adapter;

import com.baiyan.ddd.base.model.ddd.Adapter;
import com.baiyan.ddd.core.infrastructure.adapter.model.UnitDTO;

/**
 * 单位rpc适配器【对外rpc防腐层】
 *
 * @author baiyan
 */
public interface UnitAdapter extends Adapter {

    /**
     * 根据单位id获取单位
     * @param unitId
     * @return
     */
    UnitDTO findUnitByUnitId(Long unitId);

}
