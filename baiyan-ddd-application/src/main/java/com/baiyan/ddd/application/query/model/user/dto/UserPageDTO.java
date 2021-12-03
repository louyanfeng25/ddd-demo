package com.baiyan.ddd.application.query.model.user.dto;

import com.baiyan.ddd.application.query.model.role.dto.RoleDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户列表页展示实体
 *
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageDTO extends UserDTO {

    /**
     * 角色信息
     */
    List<RoleDTO> roles;
}
