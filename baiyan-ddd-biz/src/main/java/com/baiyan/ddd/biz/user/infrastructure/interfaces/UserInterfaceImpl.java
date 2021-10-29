package com.baiyan.ddd.biz.user.infrastructure.interfaces;

import com.baiyan.ddd.biz.user.interfaces.UserInterface;
import com.baiyan.ddd.biz.user.interfaces.model.UnitDTO;
import com.baiyan.ddd.share.rpc.UnitApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户灰度层，领域层防腐
 *
 * @author baiyan
 */
@Component
@Slf4j
public class UserInterfaceImpl implements UserInterface {

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
