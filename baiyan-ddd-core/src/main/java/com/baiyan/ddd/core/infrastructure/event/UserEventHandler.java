package com.baiyan.ddd.core.infrastructure.event;

import com.baiyan.ddd.core.user.event.UserDeleteEvent;
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
        //用户删除后，用户相关业务数据删除
    }

}
