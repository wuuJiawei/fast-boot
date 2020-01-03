package com.w.core.crud;

import cn.hutool.core.lang.Dict;
import com.w.core.util.PageRet;
import com.w.core.util.Ret;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;

/**
 * 单个数据查询
 * 
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/3
 */
public interface IDetail<T> {

    /**
     * 查询后重写响应数据
     * @param t
     * @return
     */
    Ret<T> detailAfter(T t);

    /**
     * 数据查询
     * @param id
     * @return
     */
    Ret<T> detail(String id);

}
