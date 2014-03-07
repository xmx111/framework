package com.ufo.framework.core.dao.exception;

/**
 * 
 * 类名称：ObjectNotFoundException 
 * 类描述： 
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 下午5:35:59 
 * @version 0.1
 *
 */
public class ObjectNotFoundException extends DaoException {

    /** 
     *serialVersionUID 
     */ 
    private static final long serialVersionUID = 4297614039056010711L;

    /**
     * Create a new ObjectNotFoundException with the specified message.
     * @param msg the detail message
     */
    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Create a new ObjectNotFoundException with the specified message
     * and root cause.
     * @param msg the detail message
     * @param ex the root cause
     */
    public ObjectNotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }
    
    /**
     * Create a new ObjectNotFoundException with the specified message.
     * @param msg the detail message
     */
    public ObjectNotFoundException(String messageCode, Object[] messageArgs, String defaultMessage) {
        super(messageCode, messageArgs, defaultMessage);
    }

    /**
     * Create a new ObjectNotFoundException with the specified message
     * and root cause.
     * @param msg the detail message
     * @param ex the root cause
     */
    public ObjectNotFoundException(String messageCode, Object[] messageArgs, String defaultMessage, Throwable ex) {
        super(messageCode, messageArgs, defaultMessage, ex);
    }
}