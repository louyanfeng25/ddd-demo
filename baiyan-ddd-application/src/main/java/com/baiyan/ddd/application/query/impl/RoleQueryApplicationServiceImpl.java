package com.baiyan.ddd.application.query.impl;

import com.baiyan.ddd.application.query.RoleQueryApplicationService;
import com.baiyan.ddd.application.query.model.role.dto.RoleDTO;
import com.baiyan.ddd.infrastructure.db.mapper.RoleMapper;
import com.baiyan.ddd.infrastructure.db.model.RolePO;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baiyan
 */
@Repository
public class RoleQueryApplicationServiceImpl implements RoleQueryApplicationService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> list(List<Long> roleIds){
        if(CollectionUtils.isEmpty(roleIds)){
            return null;
        }
        List<RolePO> pos = roleMapper.selectBatchIds(roleIds);
        if(CollectionUtils.isEmpty(pos)){
            return null;
        }
        return pos.stream().map(po->{
            RoleDTO dto = new RoleDTO();
            BeanUtils.copyProperties(po,dto);
            return dto;
        }).collect(Collectors.toList());
    }

}
