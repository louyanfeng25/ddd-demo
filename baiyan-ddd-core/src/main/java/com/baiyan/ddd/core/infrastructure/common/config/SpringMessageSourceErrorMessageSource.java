package com.baiyan.ddd.core.infrastructure.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author baiyan
 */
@Component
public class SpringMessageSourceErrorMessageSource {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Object... params) {
        return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, String defaultMessage, Object... params) {
        return messageSource.getMessage(code, params, defaultMessage, LocaleContextHolder.getLocale());
    }
}
