package com.w.core.crud;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.w.core.util.PageRet;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/2
 */
public class QueryKit<T> {

    public static final String QUERY_ORDER_FIELD = "field";

    public static final String QUERY_ORDER_TYPE = "type";

    public static final String QUERY_ORDER_ASC = "asc";

    public static final String QUERY_ORDER_PAGE = "page";

    public static final String QUERY_ORDER_LIMIT = "limit";

    /**
     * 按约定获取request的查询参数，组合成Query
     * 约定的查询参数格式：param@[type]@[column]，
     * [type]：（查询的方式），like/eq/lt/gt/between
     * [column]：（数据库中的字段名称）；支持多个字段，字段名称使用[|]分割；当type为between时，字段对应的值使用[ - ]分割，注意前后有空格
     * 示例：param@like@user_name
     * 示例：param@like@user_name|phone|nick_name
     * 示例：param@between@time, 值为2000-01-01 - 2000-01-31
     *
     * @return
     */
    public static <T> Query<T> newQueryParam(Query<T> query, HttpServletRequest request) {
        String attrParam = "param", attrMultiColumn = "|", attrColumn = "@";

        Map<String, String> requestParam = getParamMapNotBlank(request);

        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.indexOf(attrParam) != 0) {
                continue;
            }
            if (StrUtil.isBlank(value)) {
                continue;
            }

            String[] arr = StrUtil.split(key, attrColumn);
            String queryType = arr[1],
                    columnName = arr[2];

            // and(a=a or b=b) 嵌套条件查询
            if (columnName.contains(attrMultiColumn)) {
                String[] multiArr = StrUtil.split(columnName, attrMultiColumn);


            } else {
                query = fixWrapper(queryType, columnName, value, query);
            }
        }

        return query;
    }

    /**
     * 根据queryType组合Wrapper
     *
     * @param queryType
     * @param columnKey
     * @param columnValue
     * @param wrapper
     * @return
     */
    private static <T> Query<T> fixWrapper(String queryType, String columnKey, String columnValue, Query<T> wrapper) {
        String attrLike = "like", attrLt = "lt", attrGt = "gt", attrEq = "eq", attrBetween = "between";
        String columnName = columnKey, value = columnValue;
        // like
        if (attrLike.equals(queryType)) {
            wrapper.andLike(columnName, "%" + value + "%");
        }
        // lt
        else if (attrLt.equals(queryType)) {
            wrapper.andLess(columnName, value);
        }
        // gt
        else if (attrGt.equals(queryType)) {
            wrapper.andGreat(columnName, value);
        }
        // eq
        else if (attrEq.equals(queryType)) {
            wrapper.andEq(columnName, value);
        }
        // between: 以[ - ]作为分隔符
        else if (attrBetween.equals(queryType)) {
            String[] vArr = StrUtil.split(value, " - ");
            wrapper.andBetween(columnName, vArr[0], vArr[1]);
        }
        return wrapper;
    }

    /**
     * 获取非空的参数kv
     *
     * @return
     */
    private static Map<String, String> getParamMapNotBlank(HttpServletRequest request) {
        Map<String, String> requestParam = ServletUtil.getParamMap(request);
        Map<String, String> newParamMap = MapUtil.newHashMap();
        for (String key : requestParam.keySet()) {
            String value = requestParam.get(key);
            if (StrUtil.isNotBlank(value)) {
                newParamMap.put(key, value);
            }
        }
        return newParamMap;
    }

    /**
     * 创建分页响应值
     *
     * @param pageQuery
     * @param <T>
     * @return
     */
    public static <T> PageRet<T> createRet(PageQuery<T> pageQuery) {
        PageRet<T> ret = new PageRet<>();
        ret.setCode(0);
        ret.setCount(pageQuery.getTotalRow());
        ret.setData(pageQuery.getList());
        ret.setMsg("");
        return ret;
    }

    /**
     * 分页查询
     * @param page 当前页码
     * @param limit 每页数量
     * @param query 已创建的查询条件
     * @param <T>
     * @return
     */
    public static <T> PageQuery<T> page(Integer page, Integer limit, Query<T> query) {
        page = page == null ? 1 : page;
        limit = limit == null ? 20 : limit;
        PageQuery<T> pageQuery = query.pageSimple(page, limit);
        return pageQuery;
    }

}
