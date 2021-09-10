package com.baiyan.ddd.base.model.ddd;

import java.io.Serializable;

/**
 * 基础仓储接口
 *
 * @author baiyan
 */
public interface Repository<AGGREGATE, ID extends Serializable>{

    /**
     * 删除
     *
     * @param id
     */
    void delete(ID id);

    /**
     * 按id查找
     *
     * @param id
     * @return
     */
    AGGREGATE byId(ID id);

    /**
     * 保存或更新聚合根
     *
     * @param aggregate
     * @param <S>
     * @return
     */
    <S extends AGGREGATE> S save(S aggregate);

}
