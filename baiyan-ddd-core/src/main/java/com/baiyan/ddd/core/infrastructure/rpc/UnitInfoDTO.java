package com.baiyan.ddd.core.infrastructure.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 单位信息【三方服务的实体】
 * @author baiyan
 */
@Data
@AllArgsConstructor
public class UnitInfoDTO {

    /**
     * 单位id
     */
    private Long id;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 单位地址
     */
    private String position;

}
