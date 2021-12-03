package com.baiyan.ddd.infrastructure.common.event;

import com.baiyan.ddd.base.model.event.BaseDomainEvent;
import com.baiyan.ddd.base.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


/**
 * 领域事件发布实现类
 *
 * @author baiyan
 */
@Component
@Slf4j
public class DomainEventPublisherImpl implements common.event.DomainEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(BaseDomainEvent event) {
        log.debug("发布事件,com.baiyan.ddd.biz.user.infrastructure.handler:{}", GsonUtil.gsonToString(event));
        applicationEventPublisher.publishEvent(event);
    }

}
