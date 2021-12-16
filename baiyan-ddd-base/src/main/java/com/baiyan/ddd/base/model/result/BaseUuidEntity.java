package com.baiyan.ddd.base.model.result;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 基础表结构实体
 *
 * @author baiyan
 */
@Data
public class BaseUuidEntity {

    /**
     * 主键id 采用默认雪花算法
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * mybatis-plus支持的数据软删标识
     *
     * 是否删除，0为未删除
     */
    @TableLogic(delval = "current_timestamp()")
    private Long deleted;
}
