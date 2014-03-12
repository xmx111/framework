package com.ufo.framework.core.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;


/**
 * 
 * 类名称：Department 
 * 类描述： 部门
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-8 上午11:03:19 
 * @version 0.1
 *
 */
@Entity
@Table(name = "ufo_sys_department")
public abstract class Department extends UnDeleteEntity {
    
    /** 
     * institution 关联机构
     */
    private Organization organization;
    
    /** 
     * parent 上级部门
     */
    private Department parent;
    
    /** 
     * name 名称
     */
    private String name;
    
    /** 
     * description 描述
     */
    private String description;

    /**
     * isStock 是否库存点
     */
    private Boolean isStock = Boolean.FALSE;

    /** 
     * admins 管理员列表
     */
    private Set<Admin> admins = new HashSet<Admin>(0);
    
    @Column(length = 128)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 800)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @ForeignKey(name = "fk_parent_dept")
    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orga_id")
    @ForeignKey(name = "fk_dept_organization")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @Fetch(FetchMode.SUBSELECT)
    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

    @Column(name = "is_stock", length = 1)
    public Boolean getIsStock() {
        return isStock;
    }

    public void setIsStock(Boolean stock) {
        isStock = stock;
    }
}
