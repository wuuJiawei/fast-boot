package com.w.generator.target;


import com.w.generator.model.Entity;

public interface AutoGen {

    public void make(Target target, Entity entity);

    public String getName();
}
