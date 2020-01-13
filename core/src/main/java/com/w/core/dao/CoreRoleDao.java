package com.w.core.dao;


import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.w.core.model.*;

/**
 * 角色表 Dao
 * @see
 * @since 2020-01-12
 */
@SqlResource("core.coreRole")
public interface CoreRoleDao extends BaseMapper<CoreRole>{

}