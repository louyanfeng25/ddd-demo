package com.baiyan.ddd.biz.user.domain.service.impl;

import com.baiyan.ddd.biz.user.domain.model.User;
import com.baiyan.ddd.biz.user.domain.service.UserDomainService;
import com.baiyan.ddd.biz.user.interfaces.UserInterface;
import com.baiyan.ddd.biz.user.interfaces.model.UnitDTO;
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
