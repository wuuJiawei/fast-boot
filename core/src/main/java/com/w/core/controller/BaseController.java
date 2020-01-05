package com.w.core.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/1
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;

    /**
     * 获得所有请求参数
     * @return
     */
    protected Dict getParamMap(){
        Map<String, String> paramMap = ServletUtil.getParamMap(request);
        Dict d = Dict.create();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            d.set(entry.getKey(), entry.getValue());
        }
        return d;
    }


}
