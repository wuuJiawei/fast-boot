package com.w.console;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/9
 */
@Component
public class HelloWorld {


    @PostConstruct
    public void hello(){
        System.out.println("Hello~~~~~~~~~~~~~~~~~~~~~~");
    }


}
