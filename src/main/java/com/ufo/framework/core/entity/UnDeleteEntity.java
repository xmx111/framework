package com.ufo.framework.core.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * 类名称：UnDeleteEntity 
 * 类描述： 非物理删除的实体数据层 
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 上午9:49:58 
 * @version 0.1
 *
 */
@MappedSuperclass
public class UnDeleteEntity extends IdEntity {

    /** 
    *deleted 是否删除  0=false=未删除,1=true=已删除
    */ 
    protected Boolean deleted = Boolean.FALSE;

    @Column
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
