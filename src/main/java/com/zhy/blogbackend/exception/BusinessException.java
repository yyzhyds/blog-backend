package com.zhy.blogbackend.exception;


import com.zhy.blogbackend.common.ErrorCode;

/**
 * @author 随缘而愈
 * @version 2.0
 * @description 自定义异常
 * @date 21/2/2024 下午2:12
 */

public class BusinessException extends RuntimeException{

    /**
     * 异常码
     */
    private final int code;

    /**
     * 描述
     */
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
