package com.baiyan.ddd.application.query.model.role.dto;

import com.baiyan.ddd.base.model.ddd.Representation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色信息
 * @author baiyan
 * @date 2021/12/03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Representation {

    /**
     * 角色id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

}
