package com.w.core.crud;

import com.w.core.util.Ret;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/3
 */
public interface IUpdate<T> {


    /**
     * 数据更新前，支持做各种操作
     * @param m
     * @return
     */
    EditResult<T> updateBefore(T m);

    /**
     * 数据更新完成后，支持做各种骚操作
     * @param m
     * @return
     */
    Ret<T> updateAfter(T m);

    /**
     * 数据插入
     * @param m
     * @return
     */
    Ret<T> update(T m);

}
