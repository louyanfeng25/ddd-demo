package com.baiyan.ddd.domain.share.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 领域事件业务类型枚举
 *
 * @author baiyan
 * @date 2021/12/14
 */
@AllArgsConstructor
public enum DomainEventEnum {

    USER_CREATE("user_create","用户删除事件"),
    USER_UPDATE("user_update","用户修改事件"),
    USER_DELETE("user_delete","用户删除事件"),
    ;

    @Getter
    private String key;

    @Getter
    private String value;

}
