package com.w.core.model;

import java.util.Date;

import lombok.Data;
import org.beetl.sql.core.annotatoin.AutoID;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 数据字典
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/1
 */
@Data
public class CoreDict extends BaseModel {

    @AutoID
    private Long id;

    //删除标识
    @JsonIgnore
    protected Integer delFlag = 0;

    //创建时间
    protected Date createTime;

    //类型
    private String type;

    //类型描述
    private String typeName;

    // 数据值
    private String value;

    // 标签名
    private String name;

    // 排序
    private Integer orders;

    //父Id
    private Long parent;

    //备注
    private String remark;

}
