package com.baiyan.ddd.core.user.domain.service.impl;

import com.baiyan.ddd.core.infrastructure.adapter.UnitAdapter;
import com.baiyan.ddd.core.infrastructure.adapter.model.UnitDTO;
import com.baiyan.ddd.core.user.domain.model.User;
import com.baiyan.ddd.core.user.domain.service.UserDomainService;
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
    UnitAdapter unitAdapter;


    @Override
    public void associatedUnit(Long unitId, User user){
        UnitDTO unitByUnitId = unitAdapter.findUnitByUnitId(unitId);
        user.bindUnit(unitId,unitByUnitId.getUnitName());
    }

}
