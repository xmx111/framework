package com.ufo.framework.core.security.entity;

import java.util.Set;

import com.ufo.framework.core.security.dto.IAdminDto;

/**
 * 
 * 类名称：IAdmin 
 * 类描述： 管理员接口
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 上午10:24:12 
 * @version 0.1
 *
 */
public interface IAdmin {
    
    /**
     * 
     * 取所有角色对象
     * @return
     * 
     */
    public Set<IRole> getRoles();
    
    /**
     * 
     * 转Dto
     * @return
     * 
     */
    public IAdminDto toIAdminDto();
}
