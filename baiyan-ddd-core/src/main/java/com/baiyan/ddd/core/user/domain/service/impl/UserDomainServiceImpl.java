package com.baiyan.ddd.core.user.domain.service.impl;

import com.baiyan.ddd.core.infrastructure.adapter.model.UnitDTO;
import com.baiyan.ddd.core.user.domain.model.User;
import com.baiyan.ddd.core.user.domain.service.UserDomainService;
import com.baiyan.ddd.core.user.interfaces.UserInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户领域服务
 *
 * @author baiyan
 */
@Slf4j
@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    UserInterface userInterface;


    @Override
    public void associatedUnit(Long unitId, User user){
        UnitDTO unitByUnitId = userInterface.findUnitByUnitId(unitId);
        user.bindUnit(unitId,unitByUnitId.getUnitName());
    }

}
