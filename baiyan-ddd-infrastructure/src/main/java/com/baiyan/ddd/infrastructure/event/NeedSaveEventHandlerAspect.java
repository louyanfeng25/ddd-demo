package com.baiyan.ddd.infrastructure.event;

import com.baiyan.ddd.base.model.result.BaseResult;
import com.baiyan.ddd.base.model.result.Result;
import com.baiyan.ddd.domain.share.event.BaseDomainEvent;
import com.baiyan.ddd.domain.share.event.DomainEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author baiyan
 * @since 2021/12/14
 */
@Aspect
@Component
@Slf4j
public class NeedSaveEventHandlerAspect {

    @Resource
    private DomainEventRepository domainEventRepository;

    @Pointcut("@annotation(com.baiyan.ddd.domain.share.event.NeedSaveEventResult)")
    public void pointcut() {
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Result result) {
        BaseDomainEvent baseEvent = (BaseDomainEvent)joinPoint.getArgs()[0];
        if(Objects.equals(result.getCode(), BaseResult.CODE_SUCCESS)){
            // 更新事件状态为成功
            log.info("更新事件{}状态为成功", baseEvent.getId());
            baseEvent.handleSuccess();
        }else {
            // 更新状态失败
            log.info("更新事件{}状态为失败", baseEvent.getId());
            baseEvent.handleFailed();
        }
        domainEventRepository.update(baseEvent);
    }

}
