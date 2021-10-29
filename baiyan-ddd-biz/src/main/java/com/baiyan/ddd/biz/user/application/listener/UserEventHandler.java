package com.baiyan.ddd.biz.user.application.listener;

import com.baiyan.ddd.biz.user.domain.event.UserDeleteEvent;
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
    public void handleEvent(UserDeleteEvent event) {
        //用户删除后，后续执行强相关的链式调用逻辑
    }

}
