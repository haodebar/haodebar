package com.chaoyue.common.utils;

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

    /**
     * 成功
     */
    public static final int ERRCODE_SUCCESS = 0;
    /**
     * 失败
     */
    public static final int ERRCODE_FAIL = 1;

    private int status;//状态
    private String msg;// 消息提示
    private T data; //数据集合

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
    public static <T> Result<T> createOK() {
        Result<T> ok = new Result(ERRCODE_SUCCESS, "");
        return ok;
    }

    /**
     * 返回访问失败,返回数据集合
     */
    public static <T> Result<T> createOK(T data) {
        Result<T> ok = createOK();
        ok.setData(data);
        return ok;
    }

    /***
     * 返回访问成功,返回指定消息提醒
     */
    public static <T> Result<T> createOK(String msg) {
        Result<T> ok = new Result(ERRCODE_SUCCESS,msg);
        return ok;
    }

    /***
     * 返回访问成功,返回指定code 与消息提醒
     */
    public static <T> Result<T> createOK(int code,String msg) {
        Result<T> ok = new Result(code,msg);
        return ok;
    }

    /***
     * 返回访问失败,无消息提醒
     */
    public static <T> Result<T> createFail() {
        Result<T> fail = new Result(ERRCODE_FAIL, "");
        return fail;
    }

    /***
     * 返回访问失败,传递消息提醒
     */
    public static <T> Result<T> createFail(String message) {
        Result<T> fail = new Result(ERRCODE_FAIL, "");
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
    	Result<T> fail = new Result(ERRCODE_FAIL, "");
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
