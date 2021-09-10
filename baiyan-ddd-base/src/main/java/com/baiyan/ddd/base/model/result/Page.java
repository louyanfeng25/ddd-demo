package com.baiyan.ddd.base.model.result;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 分页实体
 *
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return getRecords().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        getRecords().forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return getRecords().spliterator();
    }

    @Override
    public <R> Page<R> convert(Function<? super T, ? extends R> mapper) {
        return (Page<R>) super.convert(mapper);
    }

    @Override
    public long getCurrent() {
        return super.getCurrent();
    }

    /**
     * 反序列化的时候需要用到
     */
    public void setOffset(Long offset) {
        setCurrent(offset/getSize()+1);
    }

    @Override
    public long getSize() {
        return super.getSize();
    }

    /**
     * 反序列化的时候需要用到
     */
    public void setLimit(Long limit) {
        setSize(limit);
    }

    @Override
    public List<OrderItem> getOrders() {
        return super.getOrders();
    }

    @Override
    public boolean isSearchCount() {
        return super.isSearchCount();
    }
}

