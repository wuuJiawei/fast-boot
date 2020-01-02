package com.w.core.crud;

import cn.hutool.core.lang.Dict;
import org.beetl.sql.core.query.Query;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/2
 */
public class QueryUtil<T> {

    /**
     * 按约定获取request的查询参数，组合成Query
     * 约定的查询参数格式：param|[type]|[column]，
     * [type]：（查询的方式），like/eq/lt/gt/between
     * [column]：（数据库中的字段名称）；支持多个字段，字段名称使用[|]分割；当type为between时，字段对应的值使用[ - ]分割，注意前后有空格
     * 示例：param@like@user_name
     * 示例：param@like@user_name|phone|nick_name
     * 示例：param@between@time, 值为2000-01-01 - 2000-01-31
     * @return
     */
    public static <T> Query<T> newQueryParam(Query<T> query, Dict paramDict){

    }

}
