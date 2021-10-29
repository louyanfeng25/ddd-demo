package com.baiyan.ddd.biz.user.infrastructure.repository;

import cn.hutool.core.collection.CollUtil;
import com.baiyan.ddd.biz.user.domain.model.User;
import com.baiyan.ddd.biz.user.domain.repository.UserRepository;
import com.baiyan.ddd.biz.user.infrastructure.converter.UserConverter;
import com.baiyan.ddd.biz.user.infrastructure.repository.mapper.RoleMapper;
import com.baiyan.ddd.biz.user.infrastructure.repository.mapper.UserMapper;
import com.baiyan.ddd.biz.user.infrastructure.repository.mapper.UserRoleMapper;
import com.baiyan.ddd.biz.user.infrastructure.repository.po.RolePO;
import com.baiyan.ddd.biz.user.infrastructure.repository.po.UserPO;
import com.baiyan.ddd.biz.user.infrastructure.repository.po.UserRolePO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void delete(Long id){
        userRoleMapper.delete(Wrappers.<UserRolePO>lambdaQuery().eq(UserRolePO::getUserId,id));
        userMapper.deleteById(id);
    }

    @Override
    public User byId(Long id){
        UserPO user = userMapper.selectById(id);
        if(Objects.isNull(user)){
            return null;
        }
        List<UserRolePO> userRoles = userRoleMapper.selectList(Wrappers.<UserRolePO>lambdaQuery()
                .eq(UserRolePO::getUserId, id).select(UserRolePO::getRoleId));
        List<Long> roleIds = CollUtil.isEmpty(userRoles) ? new ArrayList<>() : userRoles.stream()
                .map(UserRolePO::getRoleId)
                .collect(Collectors.toList());
        List<RolePO> roles = roleMapper.selectBatchIds(roleIds);
        return UserConverter.deserialize(user,roles);
    }


    @Override
    public User save(User user){
        UserPO userPo = UserConverter.serializeUser(user);
        if(Objects.isNull(user.getId())){
            userMapper.insert(userPo);
            user.setId(userPo.getId());
        }else {
            userMapper.updateById(userPo);
            userRoleMapper.delete(Wrappers.<UserRolePO>lambdaQuery().eq(UserRolePO::getUserId,user.getId()));
        }
        List<UserRolePO> userRolePos = UserConverter.serializeRole(user);
        userRolePos.forEach(userRoleMapper::insert);
        return this.byId(user.getId());
    }

}
