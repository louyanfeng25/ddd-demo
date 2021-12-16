package com.baiyan.ddd.domain.aggregate.role.model;

import com.baiyan.ddd.base.model.ddd.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 角色聚合根
 *
 * @author baiyan
 */
@Data
@AllArgsConstructor
@Builder
public class Role implements AggregateRoot {

    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色code
     */
    private String code;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


    /**
     * 获取用户角色标签
     *
     * @return
     */
    public String getUserTag(){
        return Objects.equals(this.code,"admin") ? "管理员" : "普通用户";
    }

}
