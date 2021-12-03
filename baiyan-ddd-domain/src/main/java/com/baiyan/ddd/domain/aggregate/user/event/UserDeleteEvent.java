package com.baiyan.ddd.domain.aggregate.user.event;

import com.baiyan.ddd.base.model.event.BaseDomainEvent;

/**
 * 用户删除领域事件
 *
 * @author baiyan
 */
public class UserDeleteEvent extends BaseDomainEvent<Long> {

    public UserDeleteEvent(Long id) {
        super(id);
    }

}
