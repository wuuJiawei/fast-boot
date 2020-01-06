package com.w.core.crud;

import com.w.core.util.Ret;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/3
 */
@Data
@Accessors(chain = true)
public class EditResult<T> {

    /**
     * 返回值
     */
    private Ret<T> result;

    /**
     * 产生的新实体对象
     */
    private T m;

    /**
     * 是否往下执行
     */
    private boolean next;

    /**
     * 继续执行CRUD的操作
     *
     * @param m
     * @return
     */
    public static <T> EditResult<T> next(T m) {
        return next(m, Ret.ok());
    }

    /**
     * 继续执行CRUD的操作
     *
     * @param m
     * @param ret
     * @return
     */
    public static <T> EditResult<T> next(T m, Ret<T> ret) {
        EditResult<T> result = new EditResult<>();
        result.setM(m)
                .setNext(true)
                .setResult(ret);
        return result;
    }

    /**
     * 直接渲染，不再执行CRUD的操作
     *
     * @param ret
     * @return
     */
    public static <T> EditResult<T> stop(Ret<T> ret) {
        return stop(null, ret);
    }

    /**
     * 直接渲染，不再执行CRUD的操作
     *
     * @param m
     * @param ret
     * @return
     */
    public static <T> EditResult<T> stop(T m, Ret<T> ret) {
        EditResult<T> result = new EditResult<>();
        result.setM(m)
                .setNext(false)
                .setResult(ret);
        return result;
    }

}
