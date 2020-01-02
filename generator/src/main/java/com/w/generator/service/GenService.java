package com.w.generator.service;

import com.w.generator.entity.ColumnInfo;
import com.w.generator.entity.TableInfo;

import java.util.List;

/**
 * 代码生成 服务层
 *
 * @author ruoyi
 */
public interface GenService {
    /**
     * 查询数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    List<TableInfo> selectTableList(String tableName);

    /**
     * 查询数据库表
     * @param tableName
     * @return
     */
    TableInfo selectTable(String tableName);

    /**
     * 查询表字段
     * @param tableName
     * @return
     */
    List<ColumnInfo> selectTableColumns(String tableName);

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] generatorCode(String[] tableNames);
}
