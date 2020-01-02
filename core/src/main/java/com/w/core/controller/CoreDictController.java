package com.w.core.controller;

import cn.hutool.extra.tokenizer.Result;
import com.w.core.crud.CrudController;
import com.w.core.dao.CoreDictDao;
import com.w.core.model.CoreDict;
import com.w.core.util.Ret;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/1
 */
@Controller
@RequestMapping
public class CoreDictController extends CrudController<CoreDict, CoreDictDao>{

    @Autowired
    CoreDictDao dictDao;

    @Override
    protected String orderField() {
        return "id";
    }

    @Override
    protected String view() {
        return "com/w/core/CoreDict/index.html";
    }


    @RequestMapping("dict")
    @ResponseBody
    public Object index(String type){
        return dictDao.findAllList(type);
    }

    @ResponseBody
    @RequestMapping("save")
    public Object save(CoreDict dict){
        dictDao.insert(dict, true);
        return "ok: " + dict.getId();
    }

    @GetMapping({"/", "index"})
    public String index(){
        return "com/w/core/CoreDict/index.html";
    }


    @Override
    public Query queryBefore(Query wrapper) {
        return super.queryBefore(wrapper);
    }

}
