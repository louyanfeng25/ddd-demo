package com.baiyan.ddd.application.query.model.user.dto;

import com.baiyan.ddd.base.model.ddd.Representation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 用户详情展示实体
 *
 * @author baiyan
 */
@Data
public class UserDTO implements Representation {

    /**
     * 用户id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String county;

    /**
     * 单位
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long unitId;

    /**
     * 单位名称
     */
    private String unitName;

}
