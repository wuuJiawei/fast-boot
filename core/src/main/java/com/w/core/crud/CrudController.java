package com.w.core.crud;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.servlet.ServletUtil;
import com.w.core.controller.BaseController;
import com.w.core.util.PageRet;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/2
 */
@Controller
public abstract class CrudController<T, S extends BaseMapper<T>> extends BaseController implements ICrudQueryService {

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

    @Override
    public Query queryBefore(Query wrapper) {
        return null;
    }

    @Override
    public PageRet queryBefore(Query query, Map paramMap) {
        return null;
    }

    @RequestMapping("query")
    @ResponseBody
    protected PageRet<T> query() {
        Dict paramDict = getParamMap();
        if (paramDict.isEmpty()) {

        }

        Query<T> query = dao.createQuery();



        return null; //TODO

    }


}
