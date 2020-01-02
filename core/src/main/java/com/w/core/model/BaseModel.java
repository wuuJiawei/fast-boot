package com.w.core.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.beetl.sql.core.TailBean;

import java.io.Serializable;
import java.util.Map;

/**
 * 用于辅助序列化beetlsql的TailBean
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/1
 */
public class BaseModel extends TailBean implements Serializable {

    protected final static String CORE_SEQ_NAME="core_seq";
    protected final static String AUDIT_SEQ_NAME="audit_seq";
    @JsonAnyGetter
    public Map<String, Object> getTails(){
        return super.getTails();
    }

}
