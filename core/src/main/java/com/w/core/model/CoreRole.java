package com.w.core.model;

import java.util.Date;
import com.w.core.model.BaseModel;

import lombok.Data;
import lombok.experimental.Accessors;
import org.beetl.sql.core.annotatoin.AutoID;


/**
 * 角色表
 * @see
 * @since 2020-01-12
 */
@Data
@Accessors(chain = true)
public class CoreRole extends BaseModel{

	//角色id
    @AutoID
    private Integer id ;
	
	//角色名称
    private String roleName ;
	
	//备注
    private String remark ;
	
	//是否禁用
    private Integer disabled ;
	
	//创建时间
    private Date ctime ;
	


}
