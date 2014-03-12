package com.ufo.framework.core.utils;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 类名称：JsonUtils 
 * 类描述： jackson工具类
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午2:03:32 
 * @version 0.1
 *
 */
public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    
    private static ObjectMapper mapper = new ObjectMapper();
    
    static {
        mapper.setSerializationInclusion(Include.NON_EMPTY);
    }

    /**
     * 
     * 把对象转成json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "{error:\"" + e.getMessage() + "\"}";
        }
    }

    /**
     * 
     * 把URL的返回字符串转成valueType类
     * @param src
     * @param valueType
     * @return
     */
    public static <T> T fromUrl(URL src, Class<T> valueType) {
        try {
            return mapper.readValue(src, valueType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /** 
     *
     * 把URL的返回字符串转成ref类
     * @param src
     * @param ref  TypeReference ref = new TypeReference<List<Integer>>() { };
     * @return
     * 
     */
    public static <T> T fromUrl(URL src, TypeReference<T> ref) {
        try {
            return mapper.readValue(src, ref);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 
     * 把content转成valueType类
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T fromString(String content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /** 
    * @param content
    * @param ref   TypeReference ref = new TypeReference<List<Integer>>() { };
    * @return
    * @return
    */
    public static <T> T fromString(String content, TypeReference<T> ref) {
        try {
            return mapper.readValue(content, ref);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
    
}
