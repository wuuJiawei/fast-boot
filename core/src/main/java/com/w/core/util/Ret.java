
package com.w.core.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Ret<T> implements Serializable {

    private Integer code = 200;

    private String message = "ok";

    private T data;

    /**
     * 成功，支持携带参数
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Ret<T> ok(T t) {
        Ret<T> ret = new Ret<>();
        ret.setCode(200)
                .setMessage("ok")
                .setData(t);
        return ret;
    }

    /**
     * 成功
     *
     * @param <T>
     * @return
     */
    public static <T> Ret<T> ok() {
        return ok(null);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Ret<T> fail(int code, String message, T t) {
        Ret<T> ret = new Ret<>();
        ret.setCode(code)
                .setMessage(message)
                .setData(t);
        return ret;
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Ret<T> fail(int code, String message) {
        return fail(code, message, null);
    }

    /**
     * 失败
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Ret<T> fail(String message) {
        return fail(500, message, null);
    }

    /**
     * 失败
     *
     * @param <T>
     * @return
     */
    public static <T> Ret<T> fail() {
        return fail(500, "网络连接异常");
    }

}


