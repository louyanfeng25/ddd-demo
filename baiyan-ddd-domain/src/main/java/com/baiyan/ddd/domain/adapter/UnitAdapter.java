package com.baiyan.ddd.domain.adapter;

import com.baiyan.ddd.base.model.ddd.Adapter;
import com.baiyan.ddd.domain.adapter.model.unit.UnitDTO;

/**
 * 单位适配器
 *
 * @author baiyan
 */
public interface UnitAdapter extends Adapter {

    /**
     * 根据单位id获取单位信息
     *
     * @param unitId
     * @return
     */
    UnitDTO byUnitId(Long unitId);
}
