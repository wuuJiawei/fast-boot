package com.w.generator.model;

import lombok.Data;


@Data
public class Attribute {

    private String name;
    private String colName;
    private String javaType;
    private String displayName;

    // 是否主键
    private boolean isId;

    // 是否查询条件
    private boolean showInQuery = true;

    // 是否在编辑表单中显示
    private boolean showInEdit = false;

    // 是否在
    private boolean showInTable = false;

    //数据字典
    private String dictType;

    // 注释
    private String comment;

    // 表单控件类型
    private String formFieldType;


}
