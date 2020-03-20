package com.yiyun.iot_monitor.utils.result;


public class NullAttrException extends RuntimeException  {
    private int code;
    private static String msg = ResultEnum.NULL_ATTR.getMsg();

    public NullAttrException() {
        super(msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
