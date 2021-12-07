package com.baiyan.ddd.application.query.impl;

import com.baiyan.ddd.application.query.RoleQueryApplicationService;
import com.baiyan.ddd.application.query.UserQueryApplicationService;
import com.baiyan.ddd.application.query.model.role.dto.RoleDTO;
import com.baiyan.ddd.application.query.model.user.dto.UserDTO;
import com.baiyan.ddd.application.query.model.user.dto.UserPageDTO;
import com.baiyan.ddd.base.model.query.KeywordQuery;
import com.baiyan.ddd.base.model.result.Page;
import com.baiyan.ddd.base.util.ValidationUtil;
import com.baiyan.ddd.domain.adapter.UnitAdapter;
import com.baiyan.ddd.domain.adapter.model.unit.UnitDTO;
import com.baiyan.ddd.infrastructure.db.mapper.UserMapper;
import com.baiyan.ddd.infrastructure.db.model.UserPO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author baiyan
 */
@Service
public class UserQueryApplicationServiceImpl implements UserQueryApplicationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UnitAdapter unitAdapter;

    @Autowired
    private RoleQueryApplicationService roleQueryApplicationService;

    @Override
    public Page<UserPageDTO> userPage(KeywordQuery query){
        Page<UserPO> pos = userMapper.userPage(query);
        return pos.convert(po->{
            UserPageDTO dto = new UserPageDTO();
            this.initUserDTO(dto,po);
            List<Long> roleIds = Arrays.stream(po.getRoleIds().split(",")).map(Long::valueOf).collect(Collectors.toList());
            List<RoleDTO> roles = roleQueryApplicationService.list(roleIds);
            dto.setRoles(roles);
            return dto;
        });
    }

    @Override
    public UserDTO detail(String userName){
        UserPO po = userMapper.selectOne(Wrappers.<UserPO>lambdaQuery().eq(UserPO::getUserName, userName));
        if(Objects.isNull(po)){
            return null;
        }
        UserDTO dto = new UserDTO();
        this.initUserDTO(dto,po);
        return dto;
    }


    /**
     * 初始化用户信息
     *
     * @param dto
     * @param po
     * @param <T>
     */
    private <T extends UserDTO> void initUserDTO(T dto, UserPO po){
        BeanUtils.copyProperties(po,dto);
        UnitDTO unit = unitAdapter.byUnitId(po.getUnitId());
        ValidationUtil.isTrue(Objects.nonNull(unit),"unit.is.not.exist");
        dto.setUnitName(unit.getUnitName());
    }

}
