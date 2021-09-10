package com.baiyan.ddd.core.infrastructure.repository.po;

import com.baiyan.ddd.base.model.result.BaseUuidEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user")
public class UserPO extends BaseUuidEntity {

    /**
     * 用户名
     * */
    private String userName;

    /**
     * 真是姓名
     * */
    private String realName;

    /**
     * 手机号
     * */
    private String phone;

    /**
     * 密码
     * */
    private String password;

    /**
     * 单位id
     * */
    private Long unitId;

    /**
     * 单位名称
     * */
    private String unitName;

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

}
