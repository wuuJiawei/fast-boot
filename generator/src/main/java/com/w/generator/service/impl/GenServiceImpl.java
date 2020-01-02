package com.w.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.w.generator.config.GenConfig;
import com.w.generator.entity.ColumnInfo;
import com.w.generator.entity.TableInfo;
import com.w.generator.service.GenService;
import com.w.generator.util.GenUtils;
import com.w.generator.util.VelocityInitializer;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代码生成 服务层处理
 *
 * @author ruoyi
 */
@Service
public class GenServiceImpl implements GenService {

    private static final String SELECT_TABLE_SQL = "select table_name, table_comment, create_time, update_time from information_schema.tables ";

    @Autowired
    private SQLManager sqlManager;

    /**
     * 查询数据库表信息
     *
     * @param tableName 表名
     * @return 数据库表列表
     */
    @Override
    public List<TableInfo> selectTableList(String tableName) {
        StringBuilder builder = new StringBuilder(SELECT_TABLE_SQL);
        builder.append("where table_comment <![CDATA[ <> ]]> '' and table_schema = (select database())");
        if (StrUtil.isNotEmpty(tableName)) {
            builder.append("AND table_name like concat('%', '").append(tableName).append("', '%')");
        }

        List<TableInfo> tableInfoList = sqlManager.execute(new SQLReady(builder.toString()),TableInfo.class);
        return tableInfoList;
    }

    @Override
    public TableInfo selectTable(String tableName) {
        StringBuilder builder = new StringBuilder(SELECT_TABLE_SQL);
        builder.append("where table_comment <![CDATA[ <> ]]> '' and table_schema = (select database())");
        if (StrUtil.isNotEmpty(tableName)) {
            builder.append("AND table_name = '").append(tableName).append("'");
        }

        List<TableInfo> tableInfoList = sqlManager.execute(new SQLReady(builder.toString()),TableInfo.class);
        if (tableInfoList.isEmpty()) {
            return null;
        }
        return tableInfoList.get(0);
    }

    @Override
    public List<ColumnInfo> selectTableColumns(String tableName) {
        List<ColumnInfo> list = sqlManager.execute(new SQLReady("select column_name, data_type, column_comment, extra from information_schema.columns " +
                "  where table_name = ? and table_schema = (select database()) order by ordinal_position", tableName), ColumnInfo.class);
        return list;
    }

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(tableName, zip);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            generatorCode(tableName, zip);
        }
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        // 查询表信息
        TableInfo table = selectTable(tableName);
        // 查询列信息
        List<ColumnInfo> columns = selectTableColumns(tableName);

        // 表名转换成Java属性名
        String className = GenUtils.tableToJava(table.getTableName());
        table.setClassName(className);
        table.setClassname(StrUtil.upperFirst(className));
        // 列信息
        table.setColumns(GenUtils.transColums(columns));
        // 设置主键
        table.setPrimaryKey(table.getColumnsLast());

        VelocityInitializer.initVelocity();

        String packageName = GenConfig.getPackageName();
        String moduleName = GenUtils.getModuleName(packageName);

        VelocityContext context = GenUtils.getVelocityContext(table);

        // 获取模板列表
        List<String> templates = GenUtils.getTemplates();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(GenUtils.getFileName(template, table, moduleName)));
                IoUtil.write(zip, CharsetUtil.UTF_8, true, sw.toString());
                zip.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
