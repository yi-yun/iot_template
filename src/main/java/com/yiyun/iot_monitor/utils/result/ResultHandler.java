package com.yiyun.iot_monitor.utils.result;

public class ResultHandler {

    public static ResultBean ok(Object o) {
        return new ResultBean(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), o);
    }

    public static ResultBean error(ResultEnum error) {
        return new ResultBean(error.getCode(), error.getMsg(), null);
    }

    public static ResultBean error(String e) {
        return new ResultBean(ResultEnum.EXCEPTION.getCode(), e, null);
    }
}
