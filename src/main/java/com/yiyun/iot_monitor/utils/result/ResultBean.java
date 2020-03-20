package com.yiyun.iot_monitor.utils.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ResultBean<T> {
    private int code;
    private String msg;
    private T data;

}