package com.w.core.controller;

import com.w.core.crud.CrudController;
import com.w.core.dao.CoreDictDao;
import com.w.core.model.CoreDict;
import com.w.core.util.Ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "id";
    }

    @Override
    protected String view() {
        return "com/w/core/CoreDict";
    }

    /**
     * 数据插入完成后，支持做各种骚操作
     *
     * @param m
     * @return
     */
    @Override
    public Ret<CoreDict> insertAfter(CoreDict m) {
        return super.insertAfter(m);
    }
}
