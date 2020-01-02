package com.w.generator.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.w.core.controller.BaseController;
import com.w.generator.entity.TableInfo;
import com.w.generator.service.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代码生成 操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/gen")
public class GenController extends BaseController {

    private String prefix = "tool/gen";

    @Autowired
    private GenService genService;

    @GetMapping()
    public String gen() {
        return prefix + "/gen";
    }

    @PostMapping("/list")
    @ResponseBody
    public Object list(String tableName) {
        List<TableInfo> list = genService.selectTableList(tableName);
        return list;
    }

    /**
     * 生成代码
     */
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genService.generatorCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = StrUtil.split(tables, ",");
        byte[] data = genService.generatorCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     *
     * @param response
     * @param data
     * @throws IOException
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"fast-boot.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), true, data);
    }
}
