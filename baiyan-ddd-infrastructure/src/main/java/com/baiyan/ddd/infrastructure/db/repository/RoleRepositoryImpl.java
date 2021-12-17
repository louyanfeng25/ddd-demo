package com.baiyan.ddd.infrastructure.db.repository;

import com.baiyan.ddd.domain.aggregate.role.model.Role;
import com.baiyan.ddd.domain.aggregate.role.repository.RoleRepository;
import com.baiyan.ddd.infrastructure.db.converter.RoleConverter;
import com.baiyan.ddd.infrastructure.db.mapper.RoleMapper;
import com.baiyan.ddd.infrastructure.db.model.RolePO;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * 用户领域仓储
 *
 * @author baiyan
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void delete(Long id){
        roleMapper.deleteById(id);
    }

    @Override
    public Role byId(Long id){
        RolePO role = roleMapper.selectById(id);
        if(Objects.isNull(role)){
            return null;
        }
        return RoleConverter.deserialize(role);
    }

    @Override
    public List<Role> listByIds(List<Long> ids){
        List<RolePO> rolePOS = roleMapper.selectBatchIds(ids);
        if(CollectionUtils.isEmpty(rolePOS)){
            return null;
        }
        return rolePOS.stream()
                .map(RoleConverter::deserialize)
                .collect(Collectors.toList());
    }

    @Override
    public Role save(Role role){
        RolePO rolePO = RoleConverter.serialize(role);
        if(Objects.isNull(role.getId())){
            roleMapper.insert(rolePO);
        }else {
            roleMapper.updateById(rolePO);
        }
        return RoleConverter.deserialize(rolePO);
    }

}
