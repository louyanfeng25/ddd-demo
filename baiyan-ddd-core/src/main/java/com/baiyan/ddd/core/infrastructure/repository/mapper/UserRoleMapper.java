package com.baiyan.ddd.core.infrastructure.repository.mapper;

import com.baiyan.ddd.core.infrastructure.repository.po.UserRolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联关系mapper
 *
 * @author baiyan
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRolePO> {

}
