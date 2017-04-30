package com.alibaba.chord.enums;

/**
 * Author wb-zj268791
 * Date 2017/4/25.
 * 返回码
 */

public enum ResultCode {
    SUCCESS(100001, "SUCCESS"),
    FAILED(100002, "FAILED"),
    PARAMETER_ERROR(10003, "参数错误"),
    API_CONNECT_FAILED(10004, "连接API失败"),
    SYSTEM_EXCEPTION(10005, "系统异常");
    // ....

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
