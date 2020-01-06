package ${package};

import com.w.core.crud.CrudController;
import com.w.core.crud.EditResult;
import ${basePackage}.model.*;
import ${basePackage}.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/1
 */
@Controller
@RequestMapping("${target.urlBase}/${entity.code}")
public class ${entity.name}Controller extends CrudController<${entity.name}, ${entity.name}Dao> {

    @Autowired
    ${entity.name}Dao dao;

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
}
