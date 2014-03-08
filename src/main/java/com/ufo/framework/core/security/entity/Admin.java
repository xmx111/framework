package com.ufo.framework.core.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import com.ufo.framework.core.entity.IUserEntity;
import com.ufo.framework.core.entity.UnDeleteEntity;

/**
 * 
 * 类名称：Admin 
 * 类描述：管理员类 
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-8 上午10:42:47 
 * @version 0.1
 *
 */
@Entity
@Table(name = "ufo_sys_admin")
public class Admin extends UnDeleteEntity implements Serializable, IUserEntity {

    /** 
     * serialVersionUID 
     */ 
    private static final long serialVersionUID = -8835641458116941984L;
    
    /** 
     * loginName 登陆名
     */
     private String loginName;
     
     /** 
      * userName 用户名
      */
     private String name;
     
     /** 
      * password 密码
      */
     private String password;
     
     /** 
      * email 邮箱
      */
     private String email;
     
     /** 
      * active 状态,是否可用
      */
     private Boolean active = Boolean.TRUE;
     
     /** 
      * remark 备注
      */
     private String remark;
     
     /** 
      * department 所属部门
      */
     private Department department;
     
     /** 
      * roles 可用角色
      */
     private Set<Role> roles = new HashSet<Role>(0);

     /** 
      * institution  所属机构
      */
     private Organization organization;

     @Column(name = "login_name", length = 64)
     public String getLoginName() {
         return loginName;
     }

     public void setLoginName(String loginName) {
         this.loginName = loginName;
     }

     @Column(name = "name", length = 64)
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     @Column(name = "password", length = 128)
     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     @Column(name = "email", length = 128)
     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     @Column(name = "status")
     public Boolean getActive() {
         return active;
     }

     public void setActive(Boolean active) {
         this.active = active;
     }

     @Column
     public String getRemark() {
         return remark;
     }

     public void setRemark(String remark) {
         this.remark = remark;
     }

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "dept_id")
     @ForeignKey(name = "fk_manager_dept")
     public Department getDepartment() {
         return department;
     }

     public void setDepartment(Department department) {
         this.department = department;
     }

     @ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "ufo_sys_admin_role_ref", joinColumns = { @JoinColumn(name = "admin_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
     @Fetch(FetchMode.SUBSELECT)
     @OrderBy("id")
     @ForeignKey(name = "fk_admin_role", inverseName = "fk_role_admin")
     public Set<Role> getRoles() {
         return roles;
     }

     public void setRoles(Set<Role> roles) {
         this.roles = roles;
     }

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "inst_id")
     @ForeignKey(name = "fk_admin_organization")
     public Organization getOrganization() {
         return organization;
     }

     public void setOrganization(Organization organization) {
         this.organization = organization;
     }
    
}
