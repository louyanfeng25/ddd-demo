package com.baiyan.ddd.domain.share.event;

/**
 * 领域事件发布接口
 *
 * @author baiyan
 */
public interface DomainEventPublisher<EVENT extends BaseDomainEvent> {

    /**
     * 发布事件
     *
     * @param event event
     */
    void publish(EVENT event);

    /**
     * 发布事件并保存
     *
     * @param event event
     */
    void publishAndSave(EVENT event);

}
