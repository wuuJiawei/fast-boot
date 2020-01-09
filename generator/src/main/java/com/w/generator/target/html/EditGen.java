package com.w.generator.target.html;

import com.w.generator.target.Target;
import com.w.generator.model.Entity;
import com.w.generator.target.AutoGen;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/9
 */
public class EditGen implements AutoGen {

    @Override
    public void make(Target target, Entity entity) {
        GroupTemplate gt = target.getGroupTemplate();
        Template template = gt.getTemplate("/html/edit.html");
        template.binding("entity", entity);
        template.binding("target", target);
        String content = template.render();
        target.flush(this, content);
    }

    @Override
    public String getName() {
        return "edit.html";
    }

}