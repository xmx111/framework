package com.ufo.framework.core.security;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufo.framework.core.annotation.Description;

/**
 * 
 * 类名称：SecurityMetadataSourceService 
 * 类描述： 提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-10 上午11:48:19 
 * @version 0.1
 *
 */
public class SecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource, ApplicationContextAware {

    // spring 上下文
    private ApplicationContext applicationContext;

    // 匹配器
    private PathMatcher pathMatcher = new AntPathMatcher();

    // 缓存权限
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public SecurityMetadataSourceService() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
    }
    
    // 初始化
    private void init() {
        if (applicationContext == null) {
            return;
        }
        // 取所有Controller控制器类
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(Controller.class);
        Set<String> beanNames = map.keySet();
        for (String beanName : beanNames) {
            Class<?> handlerType = applicationContext.getType(beanName);
            Set<Class<?>> handlerTypes = new LinkedHashSet<Class<?>>();
            // 取Controller控制器的mapping
            RequestMapping mapping = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
            if (null == mapping) {
                continue;
            }
            // 取缓存对象
            final SecurityContext context = SecurityContext.instance();
            // 得到Controller控制器的自定义注解
            Description desc = AnnotationUtils.findAnnotation(handlerType, Description.class);
            // 得到Controller控制器的Spring权限注解
            Secured secured = AnnotationUtils.findAnnotation(handlerType, Secured.class);
            // 缓存Controller控制器的权限描述
            initSecuredDescription(desc, secured);
            
            final String[] urls = mapping.value();
            handlerTypes.add(handlerType);
            handlerTypes.addAll(Arrays.asList(handlerType.getInterfaces()));
            // 缓存Controller控制器的方法权限和描述
            for (Class<?> currentHandlerType : handlerTypes) {
                ReflectionUtils.doWithMethods(currentHandlerType, new ReflectionUtils.MethodCallback() {
                    public void doWith(Method method) {
                        Description description = AnnotationUtils.findAnnotation(method, Description.class);
                        Secured _secured = AnnotationUtils.findAnnotation(method, Secured.class);
                        // 缓存Controller控制器的方法权限描述
                        initSecuredDescription(description, _secured);
                        if (_secured == null) {
                            return;
                        }
                        String[] value = _secured.value();
                        if (null == value || value.length == 0) {
                            return;
                        }
                        Collection<String> coll = new HashSet<String>(Arrays.asList(value));
                        RequestMapping mapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                        // 缓存Controller控制器的方法权限
                        if (mapping != null) {
                            String[] mappedPatterns = mapping.value();
                            if (mappedPatterns.length > 0) {
                                for (String mappedPattern : mappedPatterns) {
                                    String url = mappedPattern;
                                    if (!mappedPattern.startsWith("/")) {
                                        url = "/" + mappedPattern;
                                    }
                                    if (urls != null && urls.length != 0) {
                                        for (String root : urls) {
                                            url = root + url;
                                            url = url.replaceAll("//", "/");
                                            context.put(url, coll);
                                        }
                                    } else {
                                        context.put(url, coll);
                                    }
                                }
                            }
                        }
                    }
                }, ReflectionUtils.USER_DECLARED_METHODS);
            }
        }
    }

    /**
     * 
     * 缓存权限描述
     * @param desc
     * @param secured
     * 
     */
    private void initSecuredDescription(Description desc, Secured secured) {
        if (null == desc) {
            return;
        }
        // 取缓存对象
        final SecurityContext context = SecurityContext.instance();
        String[] codes = desc.code();
        if (codes.length == 1 && StringUtils.isBlank(codes[0]) && null != secured) {
            codes = secured.value();
        }
        String[] names = desc.value();
        for (int i = 0; i < codes.length; i++) {
            if (i >= names.length) {
                break;
            }
            context.putDesc(codes[i], names[i]);
        }
    }

    /**
     * 
     * 重写方法 
     * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
     * 取所有URL配置的权限。
     * 
     */
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<String> authorities = SecurityContext.instance().getAuthorities();
        List<ConfigAttribute> result = new ArrayList<ConfigAttribute>(authorities.size());
        for (String code : authorities) {
            result.add(new SecurityConfig(code));
        }
        return result;
    }

    /**
     * 
     * 重写方法 
     * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
     * 根据一个URL(obj)，找到该URL配置的权限。
     * 
     */
    public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
        String url = ((FilterInvocation) obj).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        // 使用本身对象缓存
        if (resourceMap.containsKey(url)) {
            return resourceMap.get(url);
        }
        Set<String> set = SecurityContext.instance().getUrls();
        for (String resURL : set) {
            if (pathMatcher.match(url, resURL)) {
                Set<String> authorities = SecurityContext.instance().get(resURL);
                Collection<ConfigAttribute> coll = new HashSet<ConfigAttribute>();
                for (String authority : authorities) {
                    coll.add(new SecurityConfig(authority));
                }
                resourceMap.put(url, coll);
                return coll;
            }
        }
        return null;
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

    /**
     * 
     * 重写方法 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     * 加载Spring配置文件时，如果Spring配置文件中所定义的Bean类实现了ApplicationContextAware 接口，那么在加载Spring配置文件时，会自动调用ApplicationContextAware 接口中的
     * 
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        init();
    }

}
