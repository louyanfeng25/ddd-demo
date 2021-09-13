package com.baiyan.ddd.core.user.domain.event;

import com.baiyan.ddd.base.model.event.BaseDomainEvent;
import com.baiyan.ddd.core.user.domain.model.User;

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
