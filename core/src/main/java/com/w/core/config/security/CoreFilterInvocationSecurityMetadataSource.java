package com.w.core.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/13
 */
@Component
public class CoreFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * Accesses the {@code ConfigAttribute}s that apply to a given secure object.
     * 返回请求的资源需要的角色
     *
     * @param object the object being secured
     * @return the attributes that apply to the passed in secured object. Should return an
     * empty collection if there are no applicable attributes.
     * @throws IllegalArgumentException if the passed object is not of a type supported by
     *                                  the <code>SecurityMetadataSource</code> implementation
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            String url = it.next();
            if (new AntPathRequestMatcher( url ).matches( request )) {
                return map.get( url );
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResourceDefine();
        return null;
    }

    /**
     * Indicates whether the {@code SecurityMetadataSource} implementation is able to
     * provide {@code ConfigAttribute}s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 初始化 所有资源 对应的角色
     */
    public void loadResourceDefine() {
        map = new HashMap<>();

        //TODO 从全县资源表中获取所有数据，保存到map中
        //TODO 示例：https://github.com/gf-huanchupk/SpringBootLearning/blob/master/springboot-jwt/src/main/java/com/gf/config/MyInvocationSecurityMetadataSourceService.java
    }
}
