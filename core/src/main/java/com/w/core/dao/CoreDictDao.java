package com.w.core.dao;

import com.w.core.model.CoreDict;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 数据字典DAO接口
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/1
 */
@SqlResource("core.coreDict")
public interface CoreDictDao extends BaseMapper<CoreDict> {

    /**
     * 查询某个类型下的字典集合
     * @param type 字典类型
     * @return
     */
    List<CoreDict> findAllList(String type);

    /**
     * 查询字段类型列表
     * @param delFlag 删除标记
     * @return
     */
    @SqlStatement(returnType = Map.class)
    List<Map<String, String>> findTypeList(int delFlag);

    /**
     * 根据父节点Id查询子节点数据
     * @param id 父节点id
     * @return
     */
    List<CoreDict> findChildByParent(Long id);

    int bathDelByValue(List<String> values);

}
