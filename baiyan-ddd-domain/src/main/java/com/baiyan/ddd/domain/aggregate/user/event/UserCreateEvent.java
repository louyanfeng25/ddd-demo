package com.baiyan.ddd.domain.aggregate.user.event;

import com.baiyan.ddd.base.model.event.BaseDomainEvent;
import com.baiyan.ddd.domain.aggregate.user.model.User;

/**
 * 用户新增领域事件
 *
 * @author baiyan
 */
public class UserCreateEvent extends BaseDomainEvent<User> {

    public UserCreateEvent(User user) {
        super(user);
    }
}
