package com.ufo.framework.core.security.entity;

import java.util.Set;

/**
 * 
 * 类名称：IRole 
 * 类描述： 角色接口
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 上午10:24:50 
 * @version 0.1
 *
 */
public interface IRole {

    /**
     * 
     * 取角色所有的功能权限
     * @return
     */
    public Set<IFunctionResource> getFunctions();
    
}
