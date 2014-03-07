package com.ufo.framework.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * 类名称：IdEntity 
 * 类描述： 统一定义id的entity基类.
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 上午9:48:28 
 * @version 0.1
 *
 */
//JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity {

    /**
     * id PK
     */
    protected Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
