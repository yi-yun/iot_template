package com.yiyun.iot_monitor.utils.result;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice()
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean handle(Exception e) {

        if (e instanceof NullAttrException) {
            return ResultHandler.error(ResultEnum.NULL_ATTR);
        }

        return ResultHandler.error(e.getMessage());
    }
}
