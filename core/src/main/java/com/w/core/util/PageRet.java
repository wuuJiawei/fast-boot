package com.w.core.util;

import java.util.List;

/**
 * 分页结果对象,这里以layui框架的table为标准
 *
 */
public class PageRet<T> {

    private int code; // 状态码, 0表示成功

    private String msg;  // 提示信息

    private long count; // 总数量, bootstrapTable是total

    private List<T> data; // 当前数据, bootstrapTable是rows

    public PageRet() {
    }

    public PageRet(List<T> rows) {
        this(rows, rows.size());
    }

    public PageRet(List<T> rows, long total) {
        this.count = total;
        this.data = rows;
        this.code = 0;
        this.msg = "";
    }

    public static <T> PageRet  ok(List<T> rows, long total){
        return new PageRet(rows, total);
    }

    public static <T> PageRet  error(String msg){
        PageRet<T> result = new PageRet();
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
