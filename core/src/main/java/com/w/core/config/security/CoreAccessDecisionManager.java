package com.w.core.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 决策器
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/13
 */
@Component
public class CoreAccessDecisionManager implements AccessDecisionManager {

    /**
     * Resolves an access control decision for the passed parameters.
     * 通过传递的参数来决定用户是否有访问权限
     *
     * @param authentication 包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities
     * @param o 就是FilterInvocation对象，可以得到request等web资源
     * @param collection 本次访问需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == collection || 0 >= collection.size()) {
            return;
        } else {
            String needRole;
            for(Iterator<ConfigAttribute> iter = collection.iterator(); iter.hasNext(); ) {
                needRole = iter.next().getAttribute();

                for(GrantedAuthority ga : authentication.getAuthorities()) {
                    if(needRole.trim().equals(ga.getAuthority().trim())) {
                        return;
                    }
                }
            }
            throw new AccessDeniedException("当前访问没有权限");
        }
    }

    /**
     * 是否能够处理传递的ConfigAttribute呈现的授权请求
     * @param configAttribute
     * @return
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * 是否能够为指定的安全对象（方法调用或Web请求）提供访问控制决策
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
