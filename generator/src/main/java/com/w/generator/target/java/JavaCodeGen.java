package com.w.generator.target.java;

import com.w.generator.target.AutoGen;
import com.w.generator.target.Target;
import com.w.generator.model.Entity;

public class JavaCodeGen implements AutoGen {

    public String basePackage;
    public Entity entity;
    public String basicFunctionCode = null;
    public static String CR = System.getProperty("line.separator");

    public JavaCodeGen(String basePackage, Entity entity, String basicFunctionCode) {
        this.basePackage = basePackage;
        this.entity = entity;
        this.basicFunctionCode = basicFunctionCode;
    }

    @Override
    public void make(Target target, Entity entity) {

        EntityGen entityGen = new EntityGen(this);
        entityGen.make(target, entity);

        DaoGen daoGen = new DaoGen(this);
        daoGen.make(target, entity);

        ControllerGen webGen = new ControllerGen(this);
        webGen.make(target, entity);

    }

    @Override
    public String getName() {
        return "";
    }

}




