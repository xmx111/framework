package com.ufo.framework.core.dao;

import com.ufo.framework.core.security.entity.IAdmin;

/**
 * 
 * 类名称：IAdminDao 
 * 类描述： 管理员数据访问对象接口
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 上午10:42:15 
 * @version 0.1
 *
 */
public interface IAdminDao {
    
    /**
     * 
     * 按登录名取管理员
     * @param loginName
     * @return
     * 
     */
    public IAdmin findByLoginName(String loginName);

}
