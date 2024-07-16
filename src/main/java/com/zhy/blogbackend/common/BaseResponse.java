package com.zhy.blogbackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 随缘而愈
 * @version 2.0
 * @description 通用返回类
 * @date 17/2/2024 上午9:42
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * 状态码
    */
    private int code;

    /**
     * 数据
     */
    private T data;

    /**
     * 信息
     */
    private String message;

    /**
     * 描述
     */
    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }

    public BaseResponse(int code, T data) {
        this(code, data, "", "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }
}
