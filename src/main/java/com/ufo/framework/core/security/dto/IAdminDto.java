package com.ufo.framework.core.security.dto;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * 类名称：IAdminDto 
 * 类描述： 管理员数据传输对象
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 上午10:41:17 
 * @version 0.1
 *
 */
public interface IAdminDto extends UserDetails {
    
    Integer getId();

    public void setId(Integer id);

    /**
     * 
     * 设置GrantedAuthority权限
     * @param authorities
     * 
     */
    public void setAuthorities(Collection<GrantedAuthority> authorities);
}
