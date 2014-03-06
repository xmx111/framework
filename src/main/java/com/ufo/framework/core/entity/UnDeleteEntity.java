package com.ufo.framework.core.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/** 
* 类名称：UndeleteEntity 
* 类描述：非物理删除的实体数据层 
* 
* 创建人：Duzj
* 创建时间：2012-12-23 上午10:36:58 
* @version 
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
