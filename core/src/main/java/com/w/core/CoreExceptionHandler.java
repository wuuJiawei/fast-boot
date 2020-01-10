package com.w.core;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 */
@Slf4j
@ControllerAdvice
public class CoreExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Map<String, Object> errorHandler(Throwable ex) {
        Map<String, Object> map = new HashMap<>();
        // 根据不同错误获取错误信息
        if (ex != null) {
            String message = ex.getMessage();
            map.put("code", 500);
            map.put("message", message == null || message.trim().isEmpty() ? "系统繁忙" : message);
            map.put("data", ex);
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        /*

        else if (xxx) {
            map.put("code", ((IException) ex).getCode());
            map.put("msg", ex.getMessage());
        } else if (ex instanceof UnauthorizedException) {
            map.put("code", 403);
            map.put("msg", "没有访问权限");
        }

         */

        return map;
    }

}
