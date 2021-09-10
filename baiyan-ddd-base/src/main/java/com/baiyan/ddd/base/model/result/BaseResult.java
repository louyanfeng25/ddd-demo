package com.baiyan.ddd.base.model.result;

import lombok.Data;

/**
 * 基础返回实体
 *
 * @author baiyan
 */
@Data
public class BaseResult {
    /**
     * httpCode
     */
    private Integer code;

    /**
     * 业务code
     */
    private String errorCode;

    /**
     * 业务信息
     */
    private String message;

    /**
     * 链路id
     */
    private String traceId;

    public BaseResult() {
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, String errorCode, String message) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * 通用业务请求状态码
     */
    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_SYSTEM_ERROR = 500;

    /**
     * 通用请求信息
     */
    public static final String SYSTEM_ERROR = "系统错误";
    public static final String MESSAGE_SUCCESS = "请求成功";
    public static final String QUERY_SUCCESS = "查询成功";
    public static final String INSERT_SUCCESS = "新增成功";
    public static final String UPDATE_SUCCESS = "更新成功";
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String IMPORT_SUCCESS = "导入成功";
    public static final String EXPORT_SUCCESS = "导出成功";
    public static final String DOWNLOAD_SUCCESS = "下载成功";

}

