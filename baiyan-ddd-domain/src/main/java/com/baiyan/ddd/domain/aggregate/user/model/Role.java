package com.baiyan.ddd.domain.aggregate.user.model;

import com.baiyan.ddd.base.model.ddd.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 角色实体
 * @author baiyan
 */
@Data
@AllArgsConstructor
public class Role implements Entity {

    /**
     * 角色id
     */
    private Long id;
}
