package com.baiyan.ddd.domain.aggregate.user.event;

import cn.hutool.core.lang.UUID;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import com.baiyan.ddd.domain.share.event.BaseDomainEvent;
import com.baiyan.ddd.domain.share.event.DomainEventEnum;
import com.baiyan.ddd.domain.share.event.EventStatusEnum;

import java.time.LocalDateTime;

/**
 * 用户修改领域事件
 *
 * @author baiyan
 */
public class UserUpdateEvent extends BaseDomainEvent<User> {

    public UserUpdateEvent(User user) {
        super(String.valueOf(user),
                //仅做演示，领域事件id为防止重复建议自定义雪花id
                UUID.fastUUID().toString(),
                DomainEventEnum.USER_UPDATE,
                EventStatusEnum.PENDING,
                LocalDateTime.now(),
                user
        );
    }
}
