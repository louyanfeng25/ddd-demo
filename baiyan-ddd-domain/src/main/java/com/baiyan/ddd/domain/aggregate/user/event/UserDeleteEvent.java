package com.baiyan.ddd.domain.aggregate.user.event;

import cn.hutool.core.lang.UUID;
import com.baiyan.ddd.domain.share.event.BaseDomainEvent;
import com.baiyan.ddd.domain.share.event.DomainEventEnum;
import com.baiyan.ddd.domain.share.event.EventStatusEnum;

import java.time.LocalDateTime;

/**
 * 用户删除领域事件
 *
 * @author baiyan
 */
public class UserDeleteEvent extends BaseDomainEvent<Long> {

    public UserDeleteEvent(Long id) {
        super(String.valueOf(id),
                //仅做演示，领域事件id为防止重复建议自定义雪花id
                UUID.fastUUID().toString(),
                DomainEventEnum.USER_DELETE,
                EventStatusEnum.PENDING,
                LocalDateTime.now(),
                id
        );
    }

}
