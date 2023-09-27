package com.chaoyue.common.exception;

import com.chaoyue.common.constant.ResultCodeEnum;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/27 16:49
 * @version: version 1.0
 * @dec: 业务异常
 */
@Data
public class BusinessException extends RuntimeException{
    private int code;
    private String msg;

    public BusinessException() {
        this.code = ResultCodeEnum.FAILED.code;
        this.msg = ResultCodeEnum.FAILED.msg;
    }

    public BusinessException(String message) {
        this.code = ResultCodeEnum.FAILED.code;
        this.msg = message;
    }

    public BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
