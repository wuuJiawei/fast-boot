package com.w.generator.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Entity {

    String name;
    String tableName;
    String code;

    // 字段列表
    ArrayList<Attribute> list = new ArrayList<Attribute>();

    // 主键字段
    Attribute idAttribute;

    // 默认排序字段
    Attribute orderAttribute;

    Attribute nameAttribute;

    String comment;

    // 所在系统模块
    String system;

    //是否生成excel导入导出按钮
    boolean includeExcel = false;

    //是否包含附件信息
    boolean attachment = false;

    //是否生成代码，也同时生成功能点
    boolean autoAddFunction = false;

    boolean autoAddMenu = false;




}
