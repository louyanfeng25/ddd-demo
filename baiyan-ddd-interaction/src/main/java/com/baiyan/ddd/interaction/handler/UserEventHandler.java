package com.baiyan.ddd.interaction.handler;

import com.baiyan.ddd.base.model.result.Result;
import com.baiyan.ddd.domain.aggregate.user.event.UserDeleteEvent;
import com.baiyan.ddd.domain.share.event.NeedSaveEventResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 用户事件处理
 *
 * @author baiyan
 */
@Component
@Slf4j
public class UserEventHandler {

    @TransactionalEventListener(fallbackExecution = true)
    @NeedSaveEventResult
    public Result<Object> handleEvent(UserDeleteEvent event) {
        try {
            log.info("用户删除后，后续执行强相关的链式调用逻辑");
            return Result.ok();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
