package com.chaoyue.common.exception;

import com.chaoyue.common.constant.ResultCodeEnum;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/27 16:50
 * @version: version 1.0
 * @dec: 描述信息
 */
@Data
public class SystemException extends RuntimeException{
    private int code;

    private String msg;

    public SystemException() {
        this.code = ResultCodeEnum.ERROR.code;
        this.msg = ResultCodeEnum.ERROR.msg;
    }

    public SystemException(String message) {
        this.code = ResultCodeEnum.ERROR.code;
        this.msg = message;
    }

    public SystemException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
