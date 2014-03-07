package com.ufo.framework.core.dao.exception;

import com.ufo.framework.core.exception.BaseRuntimeException;

/**
 * 
 * 类名称：DaoException 
 * 类描述： 
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 下午5:34:14 
 * @version 0.1
 *
 */
public class DaoException extends BaseRuntimeException {

    /** 
     *serialVersionUID 
     */ 
    private static final long serialVersionUID = -6427016241195482236L;

    /**
     * Create a new DAOException with the specified message.
     * @param msg the detail message
     */
    public DaoException(String msg) {
        super(msg);
    }

    /**
     * Create a new DAOException with the specified message
     * and root cause.
     * @param msg the detail message
     * @param ex the root cause
     */
    public DaoException(String msg, Throwable ex) {
        super(msg, ex);
    }


    /**
     * Create a new DAOException with the specified message.
     * @param msg the detail message
     */
    public DaoException(String messageCode, Object[] messageArgs, String defaultMessage) {
        
        super(messageCode, messageArgs, defaultMessage);
    }

    /**
     * Create a new DAOException with the specified message
     * and root cause.
     * @param msg the detail message
     * @param ex the root cause
     */
    public DaoException(String messageCode, Object[] messageArgs, String defaultMessage, Throwable ex) {
        super(messageCode, messageArgs, defaultMessage, ex);
    }
}
