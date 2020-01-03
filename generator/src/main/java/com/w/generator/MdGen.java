package com.w.generator;

import com.w.generator.model.Entity;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

public class MdGen implements AutoGen {

    static String CR = System.getProperty("line.separator");
    Entity entity = null;

    public MdGen() {

    }

    @Override
    public void make(Target target, Entity entity) {
        this.entity = entity;
        GroupTemplate gt = target.getGroupTemplate();
        Template template = gt.getTemplate("/md/entity.md");
        template.binding("entity", entity);
        template.binding("target", target);
        String content = template.render();
        target.flush(this, content);

    }

    @Override
    public String getName() {
        return entity.getCode() + ".md";
    }

}

