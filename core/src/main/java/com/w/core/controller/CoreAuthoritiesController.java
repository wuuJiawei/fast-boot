package com.w.core.controller;

import com.w.core.crud.CrudController;
import com.w.core.crud.EditResult;
import com.w.core.model.*;
import com.w.core.dao.*;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.ListIterator;

/**
 * 权限表 控制器
 * @see
 * @since 2020-01-10
 */
@Controller
@RequestMapping("core/coreAuthorities")
public class CoreAuthoritiesController extends CrudController<CoreAuthorities, CoreAuthoritiesDao> {

    @Autowired
    CoreAuthoritiesDao dao;

    @Override
    protected String orderField() {
        return "id";
    }

    @Override
    protected String view() {
        return "core/coreAuthorities";
    }

    /**
     * 数据插入前，支持做各种操作
     *
     * @param m
     * @return
     */
    @Override
    public EditResult<CoreAuthorities> insertBefore(CoreAuthorities m) {
        return super.insertBefore(m);
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
    public PageQuery<CoreAuthorities> queryAfter(PageQuery<CoreAuthorities> page) {
        autoConvertData(page.getList());
        return page;
    }

    /**
     * 自动填充并转换数据
     *
     * @param list
     */
    private void autoConvertData(List<CoreAuthorities> list) {
        ListIterator<CoreAuthorities> iterables = list.listIterator();
        while (iterables.hasNext()) {
            CoreAuthorities x = iterables.next();

            iterables.set(x);
        }
    }

}
