package com.baiyan.ddd.domain.adapter.model.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单位信息适配返回实体
 *
 * @author baiyan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDTO {

    /**
     * 单位id
     */
    private Long id;

    /**
     * 单位名称
     */
    private String unitName;
}
