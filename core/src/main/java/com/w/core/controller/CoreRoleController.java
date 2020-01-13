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
 * 角色表 控制器
 * @see
 * @since 2020-01-12
 */
@Controller
@RequestMapping("core/coreRole")
public class CoreRoleController extends CrudController<CoreRole, CoreRoleDao> {

    @Autowired
    CoreRoleDao dao;

    @Override
    protected String orderField() {
        return "id";
    }

    @Override
    protected String view() {
        return "core/coreRole";
    }

    /**
     * 数据插入前，支持做各种操作
     *
     * @param m
     * @return
     */
    @Override
    public EditResult<CoreRole> insertBefore(CoreRole m) {
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
    public PageQuery<CoreRole> queryAfter(PageQuery<CoreRole> page) {
        autoConvertData(page.getList());
        return page;
    }

    /**
     * 自动填充并转换数据
     *
     * @param list
     */
    private void autoConvertData(List<CoreRole> list) {
        ListIterator<CoreRole> iterables = list.listIterator();
        while (iterables.hasNext()) {
            CoreRole x = iterables.next();

            iterables.set(x);
        }
    }

}
