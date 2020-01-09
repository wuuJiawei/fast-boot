package com.w.generator.target.java;

import com.w.generator.target.AutoGen;
import com.w.generator.target.BaseTarget;
import com.w.generator.target.Target;
import com.w.generator.model.Attribute;
import com.w.generator.model.Entity;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体类
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/9
 */
public class EntityGen implements AutoGen {

    JavaCodeGen gen;

    public EntityGen(JavaCodeGen gen) {
        this.gen = gen;
    }

    @Override
    public void make(Target target, Entity entity) {
        GroupTemplate gt = target.getGroupTemplate();
        Template template = gt.getTemplate("/java/pojo.java");
        template.binding("entity", entity);
        template.binding("target", target);
        template.binding("package", gen.basePackage + ".model");
        template.binding("className", entity.getName());
        List<Map<String, Object>> attrs = new ArrayList<Map<String, Object>>();
        for (Attribute attr : entity.getList()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("comment", attr.getComment());
            map.put("type", attr.getJavaType());
            map.put("name", attr.getName());
            map.put("methodName", BaseTarget.upperFirst(attr.getName()));
            map.put("isId", attr.isId());
            map.put("dictType", attr.getDictType());
            attrs.add(map);

        }
        template.binding("attrs", attrs);
        String srcHead = "";
        srcHead += "import java.math.*;" + JavaCodeGen.CR;
        srcHead += "import java.util.Date;" + JavaCodeGen.CR;

        srcHead += "import java.sql.Timestamp;" + JavaCodeGen.CR;
        template.binding("imports", srcHead);
        template.binding("comment", entity.getComment());
        String content = template.render();
        target.flush(this, content);
    }

    @Override
    public String getName() {
        return gen.entity.getName() + ".java";
    }

}