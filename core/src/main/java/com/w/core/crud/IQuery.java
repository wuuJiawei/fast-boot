package com.w.core.crud;

import cn.hutool.core.lang.Dict;
import com.w.core.util.PageRet;
import com.w.core.util.Ret;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;

/**
 * 数据查询
 * 
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/3
 */
public interface IQuery<T> {

    /**
     * 持在查询前重写查询条件
     * @param wrapper
     * @return
     */
    Query<T> queryBefore(Query<T> wrapper);

    /**
     * 支持重写查询
     * 如果重写该方法，将不再调用CRUD的查询
     * @param query
     * @param paramMap 完整的请求参数
     * @return
     */
    PageRet<T> queryBefore(Query<T> query, Dict paramMap);

    /**
     * 查询后重写响应数据
     * 该方法的执行时机为分页查询结束后
     * 适用于查询后修改数据
     * @param page
     * @return
     */
    PageQuery<T> queryAfter(PageQuery<T> page);

    /**
     * 数据查询
     * @return
     */
    PageRet<T> query();

}
