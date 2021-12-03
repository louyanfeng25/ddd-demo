package com.baiyan.ddd.infrastructure.db.mapper;

import com.baiyan.ddd.infrastructure.db.model.RolePO;
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
