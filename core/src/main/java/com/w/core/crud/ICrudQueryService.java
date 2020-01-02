package com.w.core.crud;

import com.w.core.util.PageRet;
import com.w.core.util.Ret;
import org.beetl.sql.core.query.Query;

import java.util.Map;

/**
 *
 * 查询模块
 *
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/2
 */
public interface ICrudQueryService<T> {

    /**
     * 操作：重写查询条件
     * 场景：数据查询前
     * @param query 已经解析到的查询条件
     * @return
     */
    Query<T> queryBefore(Query<T> query);

    /**
     * 操作：重写整个查询，不经过crud的查询
     * 场景：数据查询前
     * @param query 已经解析到的查询条件
     * @param paramMap 请求参数
     * @return
     */
    PageRet<T> queryBefore(Query<T> query, Map<String, String> paramMap);

}
