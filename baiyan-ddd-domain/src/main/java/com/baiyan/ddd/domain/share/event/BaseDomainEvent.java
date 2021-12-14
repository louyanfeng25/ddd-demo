package com.baiyan.ddd.domain.share.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 领域事件基类
 *
 * @author baiyan
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDomainEvent<T> implements Serializable {

    private static final long serialVersionUID = 1465328245048581896L;

    /**
     * 幂等键:即为当前事件的id
     */
    private String id;

    /**
     * 领域对象id
     */
    private String domainId;

    /**
     * 事件状态
     */
    private EventStatusEnum eventStatus;

    /**
     * 事件类型
     */
    private DomainEventEnum eventType;

    /**
     * 业务发生时间
     */
    private LocalDateTime occurredOn;

    /**
     * 领域事件数据
     */
    private T data;

    public BaseDomainEvent(String domainId, String id, DomainEventEnum eventType, EventStatusEnum eventStatus, LocalDateTime occurredOn, T data) {
        this.domainId = domainId;
        this.id = id;
        this.eventType = eventType;
        this.eventStatus = eventStatus;
        this.data = data;
        this.occurredOn = occurredOn;
    }

    /**
     * 修改时间状态为成功
     */
    public void handleSuccess() {
        this.eventStatus = EventStatusEnum.SUCCESS;
    }

    /**
     * 修改事件状态为失败
     */
    public void handleFailed() {
        this.eventStatus = EventStatusEnum.FAILED;
    }

}
