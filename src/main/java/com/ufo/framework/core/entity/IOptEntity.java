package com.ufo.framework.core.entity;

import java.sql.Timestamp;

/**
 * 
 * 类名称：IOptEntity 
 * 类描述： 用到操作员的类的接口
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 上午9:54:19 
 * @version 0.1
 *
 */
public interface IOptEntity {
    
    /**
     * 获得操作员
    * @return
     */
    public IUserEntity getOperation();

    /**
     * 设置操作员
    * @param operation
     */
    public void setOperation(IUserEntity operation);

    /**
     * 获得操作时间
    * @return
     */
    public Timestamp getOperTime();

    /**
     * 设置操作时间
    * @param operTime
     */
    public void setOperTime(Timestamp operTime);

}
