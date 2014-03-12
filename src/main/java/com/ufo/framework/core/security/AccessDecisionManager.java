package com.ufo.framework.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * 类名称：AccessDecisionManager 
 * 类描述： 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-10 上午9:52:25 
 * @version 0.1
 *
 */
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {

    /**
     * 
     * 重写方法 
     * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
     * authentication 是登陆的角色所具有的权限列表。
     * object 是访问的url。
     * configAttributes 访问这个url所需要的权限列表。
     * 
     */
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // configAttributes为null则不需要权限
        if (configAttributes == null) {
            return;
        }

        final SecurityContext context = SecurityContext.instance();
        // 迭代所需要的权限
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        Set<String> set = new HashSet<String>();
        while(ite.hasNext()){
            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig) ca).getAttribute();
            String desc = context.getDesc(needRole);
            set.add(StringUtils.isNotBlank(desc) ? desc : needRole);
            System.out.print("URL:"+object+"----needrole:"+needRole);
            // ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.trim().equals(ga.getAuthority().trim())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(ArrayUtils.toString(set));
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
    
}
