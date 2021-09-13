package com.baiyan.ddd.core.user.domain.event;

import com.baiyan.ddd.base.model.event.BaseDomainEvent;
import com.baiyan.ddd.core.user.domain.model.User;

/**
 * 用户修改领域事件
 *
 * @author baiyan
 */
public class UserUpdateEvent extends BaseDomainEvent<User> {

    public UserUpdateEvent(User user) {
        super(user);
    }
}
