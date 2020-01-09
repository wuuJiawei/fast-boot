package ${package};


import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import ${basePackage}.model.*;

/**
 * ${comment} Dao
 * \@see
 * \@since ${date(),"yyyy-MM-dd"}
 */
\@SqlResource("${target.urlBase}.${entity.code}")
public interface ${entity.name}Dao extends BaseMapper<${entity.name}>{

}