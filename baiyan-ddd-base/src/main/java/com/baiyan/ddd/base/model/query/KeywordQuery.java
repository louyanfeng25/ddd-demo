package com.baiyan.ddd.base.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关键字查询条件
 *
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KeywordQuery extends PageQuery {

    private String keyword;
}
