package com.yiyun.iot_monitor.controller;

import com.yiyun.iot_monitor.component.MqttSender;
import com.yiyun.iot_monitor.utils.result.ResultBean;
import com.yiyun.iot_monitor.utils.result.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MqttSender mqttSender;

    @GetMapping("/test")
    public ResultBean test() {
        mqttSender.sendToMqtt("test", 2, "测试");
        return ResultHandler.ok("测试");
    }
}
