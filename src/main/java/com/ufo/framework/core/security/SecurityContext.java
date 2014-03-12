package com.ufo.framework.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类名称：SecurityContext 
 * 类描述：Spring权限内容缓存对象 
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 上午9:43:36 
 * @version 0.1
 *
 */
public class SecurityContext {
    
    // url - 权限缓存
    private Map<String, Set<String>> urlMap = null;
    
    // url - 描述缓存
    private Map<String, String> descMap = null;
    
    // 单例模式
    private static SecurityContext instance;

    private SecurityContext() {
        urlMap = new HashMap<String, Set<String>>();
        descMap = new HashMap<String, String>();
    }

    public static SecurityContext instance() {
        if (instance == null) {
            instance = new SecurityContext();
        }
        return instance;
    }

    /**
     * 
     * 加载权限
     * @param url
     * @param authority
     * 
     */
    public void put(String url, String authority) {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(authority)) {
            return;
        }
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        if (!urlMap.containsKey(url)) {
            urlMap.put(url, new HashSet<String>());
        }
        urlMap.get(url).add(authority);
    }

    /**
     * 
     * 加入权限
     * @param url
     * @param authorities
     * 
     */
    public void put(String url, Collection<String> authorities) {
        if (StringUtils.isBlank(url) || CollectionUtils.isEmpty(authorities)) {
            return;
        }
        for (String authority : authorities) {
            put(url, authority);
        }
    }

    /**
     * 
     * 取所有url
     * @return
     * 
     */
    public Set<String> getUrls() {
        return urlMap.keySet();
    }

    /**
     * 
     * 取url
     * @param url
     * @return
     * 
     */
    public Set<String> get(String url) {
        return urlMap.get(url);
    }

    /** 
     * 获取所有权限
    * @return
    */
    public Set<String> getAuthorities() {
        Set<String> result = new HashSet<String>();
        Collection<Set<String>> values = urlMap.values();
        for (Set<String> set : values) {
            result.addAll(set);
        }
        return result;
    }

    /**
     * 
     * 取所有权限编码
     * @return
     * 
     */
    public List<String> getAuthoritiesCode() {
        Set<String> keySet = descMap.keySet();
        Set<String> keys = new HashSet<String>(5);
        for (String key : keySet) {
            if (StringUtils.isBlank(key)) {
                continue;
            }
            int idx = 0;
            do {
                int pos = key.indexOf(".", idx);
                String code = pos == -1 ? key : key.substring(0, pos);
                idx = pos == -1 ? key.length() : pos + 1;
                if (!keys.contains(code)) {
                    keys.add(code);
                }
            } while (idx < key.length());

        }
        return new ArrayList<String>(keys);
    }

    /**
     * 
     * 加入描述
     * @param code
     * @param name
     * 
     */
    public void putDesc(String code, String name) {
        descMap.put(code, name);
    }

    /**
     * 取描述
     * @param code
     * @return
     * 
     */
    public String getDesc(String code) {
        return descMap.get(code);
    }
}
