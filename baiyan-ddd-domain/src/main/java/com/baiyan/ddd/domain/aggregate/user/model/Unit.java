package com.baiyan.ddd.domain.aggregate.user.model;

import com.baiyan.ddd.base.model.ddd.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 单位实体
 * @author baiyan
 * @date 2021/12/03
 */
@Data
@AllArgsConstructor
public class Unit implements Entity {

    /**
     * 单位id
     */
    private Long id;
}
