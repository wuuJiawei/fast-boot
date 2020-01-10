package com.w.core.model;

import java.util.Date;
import com.w.core.model.BaseModel;

import lombok.Data;
import lombok.experimental.Accessors;
import org.beetl.sql.core.annotatoin.AutoID;


/**
 * 权限表
 * @see
 * @since 2020-01-10
 */
@Data
@Accessors(chain = true)
public class CoreAuthorities extends BaseModel{

	//权限id
    @AutoID
    private Integer id ;
	
	//权限名称
    private String authorityName ;
	
	//授权标识
    private String authority ;
	
	//菜单url
    private String url ;
	
	//父id,0表示无父级
    private Integer parentId ;
	
	//权限类型,0菜单,1按钮
    private Integer isMenu ;
	
	//排序号
    private Integer orderNumber ;
	
	//菜单图标
    private String icon ;
	
	//创建时间
    private Date ctime ;
	


}
