package com.baiyan.ddd.core.user;

import com.baiyan.ddd.base.model.ddd.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 地址值对象
 *
 * @author baiyan
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Unit implements Entity {

    /**
     * id
     */
    private Long id;

    /**
     * 角色
     */
    private String unitName;

}
