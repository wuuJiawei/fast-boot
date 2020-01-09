package com.w.core.controller;

import cn.hutool.core.date.DateUtil;
import com.w.core.crud.CrudController;
import com.w.core.crud.EditResult;
import com.w.core.dao.CoreDictDao;
import com.w.core.enums.DelFlagEnum;
import com.w.core.model.CoreDict;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.ListIterator;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/1
 */
@Controller
@RequestMapping("core/dict")
public class CoreDictController extends CrudController<CoreDict, CoreDictDao> {

    @Autowired
    CoreDictDao dictDao;

    @Override
    protected String orderField() {
        return "orders";
    }

    @Override
    protected String view() {
        return "com/w/core/CoreDict";
    }

    /**
     * 数据插入前，支持做各种操作
     *
     * @param m
     * @return
     */
    @Override
    public EditResult<CoreDict> insertBefore(CoreDict m) {
        m.setCreateTime(DateUtil.date());
        m.setDelFlag(DelFlagEnum.NORMAL.getValue());
        m.setParent(0L);
        return EditResult.next(m);
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
    public PageQuery<CoreDict> queryAfter(PageQuery<CoreDict> page) {
        autoConvertData(page.getList());
        return page;
    }

    /**
     * 自动填充并转换数据
     *
     * @param list
     */
    private void autoConvertData(List<CoreDict> list) {
        ListIterator<CoreDict> iterables = list.listIterator();
        while (iterables.hasNext()) {
            CoreDict x = iterables.next();

            iterables.set(x);
        }
    }
}
