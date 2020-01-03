package com.w.core.crud;

import com.w.core.util.Ret;

import java.util.List;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/3
 */
public interface IDelete<T> {


    /**
     * 数据删除前，支持做各种操作
     * @param m
     * @return
     */
    EditResult<T> deleteBefore(List<String> idList);

    /**
     * 数据删除完成后，支持做各种骚操作
     * @param m
     * @return
     */
    Ret<T> deleteAfter(List<String> idList);

    /**
     * 数据删除
     * @param m
     * @return
     */
    Ret<T> delete(String id);

}
