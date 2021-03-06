package com.w.generator.target.java;

import com.w.generator.target.AutoGen;
import com.w.generator.target.Target;
import com.w.generator.model.Entity;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

/**
 * Controller
 *
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/9
 */
public class ControllerGen implements AutoGen {

    JavaCodeGen gen;

    public ControllerGen(JavaCodeGen gen) {
        this.gen = gen;
    }

    @Override
    public void make(Target target, Entity entity) {
        GroupTemplate gt = target.getGroupTemplate();
        Template template = gt.getTemplate("/java/controller.java");
        template.binding("entity", entity);
        template.binding("target", target);
        template.binding("package", gen.basePackage + ".controller");
        template.binding("basePackage", gen.basePackage);
        template.binding("basicfunctionCode", gen.basicFunctionCode);
        template.binding("comment", entity.getComment());
        String content = template.render();
        target.flush(this, content);
    }

    @Override
    public String getName() {
        return gen.entity.getName() + "Controller.java";
    }

}