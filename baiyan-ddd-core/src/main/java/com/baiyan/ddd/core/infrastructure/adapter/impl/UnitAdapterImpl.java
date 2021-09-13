package com.baiyan.ddd.core.infrastructure.adapter.impl;

import com.baiyan.ddd.core.infrastructure.adapter.UnitAdapter;
import com.baiyan.ddd.core.infrastructure.adapter.model.UnitDTO;
import com.baiyan.ddd.core.infrastructure.rpc.UnitApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 单位rpc适配器【对外rpc防腐层】
 *
 * @author baiyan
 */
@Component
public class UnitAdapterImpl implements UnitAdapter {

    @Autowired(required = false)
    UnitApi unitApi;

    @Override
    public UnitDTO findUnitByUnitId(Long unitId){
        /// 正常应该校验的逻辑如下
//        UnitInfoDTO byUnitId = unitApi.getByUnitId(unitId);
//        ValidationUtil.isTrue(Objects.nonNull(byUnitId),"unit.is.not.exist");
//        UnitDTO unitDTO = new UnitDTO();
//        unitDTO.setId(byUnitId.getId());
//        unitDTO.setUnitName(byUnitId.getUnitName());
        return new UnitDTO(12345L,"柏炎单位");
    }

}
