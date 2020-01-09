package com.w.generator.target.html;

import com.w.generator.target.Target;
import com.w.generator.model.Entity;
import com.w.generator.target.AutoGen;

public class HtmlCodeGen implements AutoGen {

    @Override
    public void make(Target target, Entity entity) {

        IndexGen indexGen = new IndexGen();
        indexGen.make(target, entity);

        EditGen editGen = new EditGen();
        editGen.make(target, entity);

    }

    @Override
    public String getName() {
        return "";
    }

}



