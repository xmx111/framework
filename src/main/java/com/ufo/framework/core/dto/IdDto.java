package com.ufo.framework.core.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * 类名称：IdDto
 * 类描述： 数据传输对象基类
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 上午9:45:07 
 * @version 0.1
 *
 */
public class IdDto implements IIdDto {
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setId(Serializable id) {
        if (id instanceof Integer) {
            this.id = (Integer) id;
        }
    }

    /**
     * 
     * 重写方法 
     * @see java.lang.Object#toString()
     * 系统中一般都要打印日志的，因为所有实体的toString()方法 都用的是简单的"+"，
     * 因为每"＋" 一个就会 new 一个 String 对象，这样如果系统内存小的话会暴内存（前提系统实体比较多）。
     * 使用ToStringBuilder就可以避免暴内存这种问题的。
     * 
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
