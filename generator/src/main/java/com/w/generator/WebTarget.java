package com.w.generator;

import com.w.generator.model.Entity;

import java.util.HashMap;
import java.util.Map;

public class WebTarget extends MavenProjectTarget {

    public Map<Object, String> map = new HashMap<Object, String>();

    public WebTarget(Entity entity, String basePackage) {
        super(entity, basePackage);
    }

    @Override
    public void flush(AutoGen gen, String content) {
        map.put(gen, content);


    }


}
