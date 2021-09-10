package com.baiyan.ddd.core.infrastructure.adapter;

import com.baiyan.ddd.core.infrastructure.adapter.model.dto.UnitDTO;
import com.baiyan.ddd.core.infrastructure.rpc.UnitApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 单位rpc适配器【对外rpc防腐层】
 *
 * @author baiyan
 */
@Component
public class UnitAdapter {

    @Autowired(required = false)
    UnitApi unitApi;

    /**
     * 根据单位id获取单位
     * @param unitId
     * @return
     */
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
