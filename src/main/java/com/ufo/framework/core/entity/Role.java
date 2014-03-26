package com.ufo.framework.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;


/**
 * 
 * 类名称：Role 
 * 类描述： 角色类
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-8 上午11:21:44 
 * @version 0.1
 *
 */
@Entity
@Table(name = "ufo_sys_role")
public class Role extends UnDeleteEntity {

    /** 
     * name 名称
     */
     private String name;
     
     /** 
      * code 编码
      */
     private String code;

     /** 
      * description 描述
      */
     private String description;
     
     /** 
      * functions 功能权限 
      */
     private Set<FunctionResource> functions;

     /** 
      * admins 管理员
      */
     private Set<Admin> admins;
     
     @Column(nullable = false, unique = true)
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     @Column(length = 24)
     public String getCode() {
         return code;
     }

     public void setCode(String code) {
         this.code = code;
     }

     @Column(length = 200)
     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     @Fetch(FetchMode.SUBSELECT)
     @JoinTable(name = "ufo_sys_role_function_resource_ref", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "function_id", referencedColumnName = "id") })
     @ForeignKey(name = "fk_role_function_resource", inverseName = "fk_function_resource_role")
     public Set<FunctionResource> getFunctions() {
         return functions;
     }

     public void setFunctions(Set<FunctionResource> functions) {
         this.functions = functions;
     }

     @ManyToMany(mappedBy = "roles")
     @Fetch(FetchMode.SUBSELECT)
     public Set<Admin> getAdmins() {
         return admins;
     }

     public void setAdmins(Set<Admin> admins) {
         this.admins = admins;
     }
}
