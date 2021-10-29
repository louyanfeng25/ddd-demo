package com.baiyan.ddd.biz.user.infrastructure.repository.mapper;

import com.baiyan.ddd.biz.user.infrastructure.repository.po.RolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色mapper
 *
 * @author baiyan
 */
@Mapper
public interface RoleMapper extends BaseMapper<RolePO> {

}
