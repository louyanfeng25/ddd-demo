package com.baiyan.ddd.infrastructure.db.model;

import com.baiyan.ddd.base.model.result.BaseUuidEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 角色实体
 *
 * @author baiyan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_role")
public class RolePO extends BaseUuidEntity {

    /** 角色名称 */
    private String name;

    /** 角色code */
    private String code;

}
