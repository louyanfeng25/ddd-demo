package com.baiyan.ddd.core.infrastructure.adapter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单位信息【三方服务的实体】
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
