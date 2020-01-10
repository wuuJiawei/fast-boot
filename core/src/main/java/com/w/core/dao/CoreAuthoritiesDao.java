package com.w.core.dao;


import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.w.core.model.*;

/**
 * 权限表 Dao
 * @see
 * @since 2020-01-10
 */
@SqlResource("core.coreAuthorities")
public interface CoreAuthoritiesDao extends BaseMapper<CoreAuthorities>{

}