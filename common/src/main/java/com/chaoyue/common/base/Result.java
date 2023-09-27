package com.chaoyue.common.base;

import com.chaoyue.common.constant.ResultCodeEnum;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
public class Result<T> {
    /**
     * constructor
     * @param status
     * @param msg
     * @param data
     */
    public Result(int status, String msg, T data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(int status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public Result() {
    }

    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    /***
     * 返回访问成功,无消息提醒
     */
    public static <T> Result<T> createOk() {
        Result<T> ok = new Result(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg());
        return ok;
    }

    /**
     * 返回访问失败,返回数据集合
     */
    public static <T> Result<T> createOk(T data) {
        Result<T> ok = createOk();
        ok.setData(data);
        return ok;
    }

    /***
     * 返回访问成功,返回指定消息提醒
     */
    public static <T> Result<T> createOk(String msg) {
        Result<T> ok = new Result(ResultCodeEnum.SUCCESS.getCode(),msg);
        return ok;
    }

    /***
     * 返回访问成功,返回指定code 与消息提醒
     */
    public static <T> Result<T> createOk(int code,String msg) {
        Result<T> ok = new Result(code,msg);
        return ok;
    }

    /***
     * 返回访问失败,无消息提醒
     */
    public static <T> Result<T> createFail() {
        Result<T> fail = new Result(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.FAILED.getMsg());
        return fail;
    }

    /***
     * 返回访问失败,传递消息提醒
     */
    public static <T> Result<T> createFail(String message) {
        Result<T> fail = new Result(ResultCodeEnum.ERROR.getCode(), "");
        fail.setMsg(message);
        return fail;
    }
    /***
     * 返回访问失败,传递失败code,消息提醒
     */
    public static <T> Result<T> createFail(int errorCode, String message) {
        return new Result(errorCode, message);
    }
    /***
     * 返回访问失败,传递消息提醒,数据集合
     */
    public static <T> Result<T> createFail(String message, T data){
    	Result<T> fail = new Result(ResultCodeEnum.ERROR.getCode(), "");
    	fail.setMsg(message);
    	fail.setData(data);
    	return fail;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
