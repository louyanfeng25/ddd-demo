package com.baiyan.ddd.core.infrastructure.interfaces;

import com.baiyan.ddd.core.infrastructure.adapter.UnitAdapter;
import com.baiyan.ddd.core.infrastructure.adapter.model.UnitDTO;
import com.baiyan.ddd.core.user.interfaces.UserInterface;
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

    @Autowired
    UnitAdapter unitAdapter;

    @Override
    public UnitDTO findUnitByUnitId(Long unitId){
       return unitAdapter.findUnitByUnitId(unitId);
    }
}
