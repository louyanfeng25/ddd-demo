package com.baiyan.ddd.biz.user.infrastructure.repository.po;

import com.baiyan.ddd.base.model.result.BaseUuidEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体
 *
 * @author baiyan
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName("t_role")
public class RolePO extends BaseUuidEntity {

    /** 角色名称 */
    private String name;

    /** 角色code */
    private String code;

}
