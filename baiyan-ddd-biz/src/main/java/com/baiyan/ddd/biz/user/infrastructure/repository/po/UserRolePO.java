package com.baiyan.ddd.biz.user.infrastructure.repository.po;

import com.baiyan.ddd.base.model.result.BaseUuidEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 用户角色关联实体
 *
 * @author baiyan
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_role")
public class UserRolePO extends BaseUuidEntity {

    /** 用户id */
    private Long userId;

    /** 角色id */
    private Long roleId;

}
