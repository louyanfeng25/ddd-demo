package com.baiyan.ddd.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统统一根异常
 *
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 430933593095358673L;

    private String errorMessage;

    private String errorCode;

    /**
     * 构造新实例。
     */
    public ServiceException() {
        super();
    }

    /**
     * 用给定的异常信息构造新实例。
     * @param errorMessage 异常信息。
     */
    public ServiceException(String errorMessage) {
        super((String)null);
        this.errorMessage = errorMessage;
    }

    /**
     * 用表示异常原因的对象构造新实例。
     * @param cause 异常原因。
     */
    public ServiceException(Throwable cause) {
        super(null, cause);
    }

    /**
     * 用异常消息和表示异常原因的对象构造新实例。
     * @param errorMessage 异常信息。
     * @param cause 异常原因。
     */
    public ServiceException(String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
    }

    /**
     * 用异常消息和表示异常原因及其他信息的对象构造新实例。
     * @param errorMessage 异常信息。
     * @param errorCode 错误代码。
     * @param cause 异常原因。
     */
    public ServiceException(String errorMessage, String errorCode, Throwable cause) {
        this(errorMessage, cause);
        this.errorCode = errorCode;
    }

    /**
     * 返回异常信息。
     * @return 异常信息。
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 返回错误代码的字符串表示。
     * @return 错误代码的字符串表示。
     */
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return getErrorMessage();
    }

    public ServiceException(String code, String message) {
        super(message);
        this.errorCode = code;
    }

}

