package com.ufo.framework.core.common;

/**
 * 
 * 类名称：RespBodyResult 
 * 类描述：@ResponseBody返回对象
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午2:19:08 
 * @version 0.1
 *
 */
public class RespBodyResult {

    /** 
     *statusCode 
     */
    private Integer statusCode = 200;
    
    /** 
     *message 消息
     */
    private String message;
    
    /** 
     *errorCode 错误号
     */
    private String errorCode;
    
    private String navTabId;
    
    private String rel;
    
    private String callbackType = "refresh";
    
    private String forwardUrl;
    
    private Object data;

    public RespBodyResult() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
