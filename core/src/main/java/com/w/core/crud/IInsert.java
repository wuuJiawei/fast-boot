package com.w.core.crud;

import com.w.core.util.Ret;

/**
 * 数据插入
 * 
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/3
 */
public interface IInsert<T> {

    /**
     * 数据插入前，支持做各种操作
     * @param m
     * @return
     */
    EditResult<T> insertBefore(T m);

    /**
     * 数据插入完成后，支持做各种骚操作
     * @param m
     * @return
     */
    Ret<T> insertAfter(T m);

    /**
     * 数据插入
     * @param m
     * @return
     */
    Ret<T> insert(T m);

}
