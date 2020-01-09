package ${package};

import com.w.core.crud.CrudController;
import com.w.core.crud.EditResult;
import ${basePackage}.model.*;
import ${basePackage}.dao.*;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.ListIterator;

/**
 * ${comment} 控制器
 * \@see
 * \@since ${date(),"yyyy-MM-dd"}
 */
\@Controller
\@RequestMapping("${target.urlBase}/${entity.code}")
public class ${entity.name}Controller extends CrudController<${entity.name}, ${entity.name}Dao> {

    \@Autowired
    ${entity.name}Dao dao;

    \@Override
    protected String orderField() {
        return "${entity.orderAttribute.name}";
    }

    \@Override
    protected String view() {
        return "${target.urlBase}/${entity.code}";
    }

    /**
     * 数据插入前，支持做各种操作
     *
     * \@param m
     * \@return
     */
    \@Override
    public EditResult<${entity.name}> insertBefore(${entity.name} m) {
        return super.insertBefore(m);
    }

    /**
     * 查询后重写响应数据
     * 该方法的执行时机为分页查询结束后
     * 适用于查询后修改数据
     *
     * \@param page
     * \@return
     */
    \@Override
    public PageQuery<${entity.name}> queryAfter(PageQuery<${entity.name}> page) {
        autoConvertData(page.getList());
        return page;
    }

    /**
     * 自动填充并转换数据
     *
     * \@param list
     */
    private void autoConvertData(List<${entity.name}> list) {
        ListIterator<${entity.name}> iterables = list.listIterator();
        while (iterables.hasNext()) {
            ${entity.name} x = iterables.next();

            iterables.set(x);
        }
    }

}
