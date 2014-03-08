package com.ufo.framework.core.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.ufo.framework.core.entity.UnDeleteEntity;

/**
 * 
 * 类名称：Organization 
 * 类描述： 组织机构
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-8 上午11:03:53 
 * @version 0.1
 *
 */
@Entity
@Table(name = "ufo_sys_organization")
public class Organization extends UnDeleteEntity implements Serializable {

    /** 
     * serialVersionUID 
     */ 
    private static final long serialVersionUID = -6235286123589761748L;
    
    /**
     * code 机构编码
     */
    private String code;
    
    /**
     * name 机构名称
     */
    private String name;
    
    /** 
     * phone 电话总机
     */
    private String phone;
    
    /** 
     * zipcode 编码 
     */
    private String zipcode;
    
    /** 
     * address 地址
     */
    private String address;
    
    /** 
     * website 网址
     */
    private String website;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 机构类型:0集团,1俱乐部,2球场,3中介机构
     */
    private Integer type;
    
    /**
     * 上级机构,自关联
     */
    private Organization parent;
    
    @Column(length = 20)
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 32)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(length = 12)
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Column(length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(length = 200)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Column(length = 1)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @ForeignKey(name = "pk_parent_organization")
    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    @Column(length = 800)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
