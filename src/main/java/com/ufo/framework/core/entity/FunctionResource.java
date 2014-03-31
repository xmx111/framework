package com.ufo.framework.core.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;


/**
 * 
 * 类名称：FunctionResource 
 * 类描述： 功能权限
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-8 上午11:37:55 
 * @version 0.1
 *
 */
@Entity
@Table(name = "ufo_sys_function_resource")
public class FunctionResource extends IdEntity {

    /** 
     * code 编码
     */
     private String code;
     
     /** 
      * name 名称
      */
     private String name;
     
     /** 
      * sequence 排序
      */
     private Integer sequence = Integer.valueOf(0);
     
     /** 
      * updateTime 手动更新时间
      */
     private Timestamp updateTime;
     
     /** 
      *roles 角色 
      */
     private Set<Role> roles;
     
     /** 
      *parent 上级权限
      */
     private FunctionResource parent;

     /** 
     *children 子权限
     */
     private List<FunctionResource> children = new ArrayList<FunctionResource>();
     
     @Column(unique = true, nullable = false)
     public String getCode() {
         return code;
     }

     public void setCode(String code) {
         this.code = code;
     }

     @Column(nullable = false)
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     @Column
     public Integer getSequence() {
         return sequence;
     }

     public void setSequence(Integer sequence) {
         this.sequence = sequence;
     }

     @Column(name = "update_time")
     public Timestamp getUpdateTime() {
         return updateTime;
     }

     public void setUpdateTime(Timestamp updateTime) {
         this.updateTime = updateTime;
     }

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "parent_id")
     @ForeignKey(name = "fk_function_resource_parent")
     public FunctionResource getParent() {
         return parent;
     }

     public void setParent(FunctionResource parent) {
         this.parent = parent;
     }

     @OneToMany(mappedBy = "parent")
     @Fetch(FetchMode.SUBSELECT)
     @OrderBy("sequence")
     public List<FunctionResource> getChildren() {
         return children;
     }

     public void setChildren(List<FunctionResource> children) {
         this.children = children;
     }

     @ManyToMany(fetch = FetchType.LAZY, mappedBy = "functions")
     @Fetch(FetchMode.SUBSELECT)
     public Set<Role> getRoles() {
         return roles;
     }

     public void setRoles(Set<Role> roles) {
         this.roles = roles;
     }
}
