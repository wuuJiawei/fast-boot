package com.w.core.crud;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.w.core.controller.BaseController;
import com.w.core.util.PageRet;
import com.w.core.util.Ret;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/2
 */
@Controller
public abstract class CrudController<T, S extends BaseMapper<T>>
        extends BaseController implements IQuery<T>, IDetail<T>, IInsert<T>, IUpdate<T>, IDelete<T> {

    @Autowired
    S dao;

    /**
     * 设置排序字段名称，
     * 如：orders
     *
     * @return
     */
    protected abstract String orderField();

    /**
     * 设置视图页面路径
     *
     * @return
     */
    protected abstract String view();

    /**
     * 打开页面前执行的操作
     */
    protected void renderPageBefore(Dict paramDict) {
    }

    /**
     * 打开页面
     *
     * @return
     */
    @GetMapping({"/", "index"})
    protected String renderPage() {
        renderPageBefore(getParamMap());
        return view();
    }

    /**
     * 支持在查询前重写查询条件
     *
     * @param wrapper
     * @return
     */
    @Override
    public Query<T> queryBefore(Query<T> wrapper) {
        return null;
    }

    /**
     * 支持重写查询
     * 如果重写该方法，将不再调用CRUD的查询
     *
     * @param query
     * @param paramMap 完整的请求参数
     * @return
     */
    @Override
    public PageRet<T> queryBefore(Query<T> query, Dict paramMap) {
        return null;
    }

    /**
     * 查询后重写响应数据
     * 该方法的执行时机为分页查询结束后
     * 适用于查询后修改数据
     *
     * @param page
     * @return
     */
    @Override
    public PageQuery<T> queryAfter(PageQuery<T> page) {
        return null;
    }

    /**
     * 数据查询
     *
     * @return
     */
    @Override
    @RequestMapping("query")
    @ResponseBody
    public PageRet<T> query() {
        Dict paramDict = getParamMap();
        Query<T> query = dao.createQuery();

        // 排序
        String field = StrUtil.isNotBlank(paramDict.getStr(QueryKit.QUERY_ORDER_FIELD)) ?
                StrUtil.toUnderlineCase(paramDict.getStr(QueryKit.QUERY_ORDER_FIELD)) :
                orderField();
        if (QueryKit.QUERY_ORDER_ASC.equals(paramDict.getStr(QueryKit.QUERY_ORDER_TYPE))) {
            query.asc(field);
        } else {
            query.desc(field);
        }

        // 解析查询参数
        query = QueryKit.newQueryParam(query, request);

        // 支持重写查询
        PageRet<T> overrideRet = queryBefore(query, paramDict);
        if (overrideRet != null) {
            return overrideRet;
        }

        // 支持在查询前重写条件
        Query<T> overrideQuery = queryBefore(query);
        if (overrideQuery != null) {
            query = overrideQuery;
        }

        PageQuery<T> pageQuery = QueryKit.page(paramDict.getInt(QueryKit.QUERY_ORDER_PAGE), paramDict.getInt(QueryKit.QUERY_ORDER_LIMIT), query);

        // 支持查询后重写响应数据
        PageQuery<T> overridePageQuery = queryAfter(pageQuery);
        if (overridePageQuery != null) {
            pageQuery = overridePageQuery;
        }
        return QueryKit.createRet(pageQuery);
    }

    /**
     * 查询后重写响应数据
     *
     * @param t
     * @return
     */
    @Override
    public Ret<T> detailAfter(T t) {
        return null;
    }

    /**
     * 数据查询
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @RequestMapping("detail")
    @ResponseBody
    @Override
    public Ret<T> detail(String id) {
        T t = dao.single(id);

        Ret<T> ret = detailAfter(t);
        if (ret != null) {
            return ret;
        }
        return Ret.ok(t);
    }

    /**
     * 数据插入前，支持做各种操作
     *
     * @param m
     * @return
     */
    @Override
    public EditResult<T> insertBefore(T m) {
        return null;
    }

    /**
     * 数据插入完成后，支持做各种骚操作
     *
     * @param m
     * @return
     */
    @Override
    public Ret<T> insertAfter(T m) {
        return null;
    }

    /**
     * 数据插入
     *
     * @param m
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @ResponseBody
    @RequestMapping("insert")
    @Override
    public Ret<T> insert(T m) {
        // 插入前支持做骚操作
        EditResult<T> editResult = insertBefore(m);
        if (editResult != null) {
            if (!editResult.isNext()) {
                return editResult.getResult();
            }
            if (editResult.getM() != null) {
                m = editResult.getM();
            }
        }

        int row = dao.createQuery().insertSelective(m);
        if (row > 0) {
            // 插入后支持做骚操作
            Ret<T> overrideRet = insertAfter(m);
            if (overrideRet != null) {
                return overrideRet;
            }
            return Ret.ok(m);
        }
        return Ret.fail();
    }

    /**
     * 数据更新前，支持做各种操作
     *
     * @param m
     * @return
     */
    @Override
    public EditResult<T> updateBefore(T m) {
        return null;
    }

    /**
     * 数据更新完成后，支持做各种骚操作
     *
     * @param m
     * @return
     */
    @Override
    public Ret<T> updateAfter(T m) {
        return null;
    }

    /**
     * 数据更新
     *
     * @param m
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @ResponseBody
    @RequestMapping("update")
    @Override
    public Ret<T> update(T m) {
        // 更新前支持做骚操作
        EditResult<T> editResult = updateBefore(m);
        if (editResult != null) {
            if (!editResult.isNext()) {
                return editResult.getResult();
            }
            if (editResult.getM() != null) {
                m = editResult.getM();
            }
        }

        dao.updateTemplateById(m);

        // 更新后支持做骚操作
        Ret<T> overrideRet = updateAfter(m);
        if (overrideRet != null) {
            return overrideRet;
        }
        return Ret.ok(m);
    }

    /**
     * 数据删除前，支持做各种操作
     *
     * @param idList
     * @return
     */
    @Override
    public EditResult<T> deleteBefore(List<String> idList) {
        return null;
    }

    /**
     * 数据删除完成后，支持做各种骚操作
     *
     * @param idList
     * @return
     */
    @Override
    public Ret<T> deleteAfter(List<String> idList) {
        return null;
    }

    /**
     * 数据删除
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @RequestMapping("delete")
    @ResponseBody
    @Override
    public Ret<T> delete(String id) {
        List<String> idArr = StrUtil.split(id, ',');
        if (idArr == null || idArr.size() == 0) {
            return Ret.fail("请选择要删除的数据");
        }

        // 删除前支持做骚操作
        EditResult<T> editResult = deleteBefore(idArr);
        if (editResult != null && !editResult.isNext()) {
            return editResult.getResult();
        }

        List<String> successIdArr = new ArrayList<>();
        int row = 0;
        for (String x : idArr) {
            int r = dao.deleteById(x);
            if (r > 0) {
                successIdArr.add(x);
                row++;
            }
        }

        if (row > 0) {
            // 更新后支持做骚操作
            Ret<T> overrideRet = deleteAfter(successIdArr);
            if (overrideRet != null) {
                return overrideRet;
            }

            return Ret.ok();
        }
        return Ret.fail();
    }
}
