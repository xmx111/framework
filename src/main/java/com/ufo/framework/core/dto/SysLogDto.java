package com.ufo.framework.core.dto;

/**
 * 
 * 类名称：SysLogDto 
 * 类描述： 系统日志类
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午3:09:42 
 * @version 0.1
 *
 */
public class SysLogDto {

    /** 
     * 
     * clientIP 访问IP
     *
     */
    private String clientIP;
    
    /** 
     * 
     * requestUrl 访问地址
     *
     */
    private String requestUrl;
    
    /** 
     * 
     * type 业务类型:1-登陆日志
     *
     */
    private Integer type;

    /** 
     * 
     * operType 操作类型,1--新增,2---修改,3--删除 
     * 
     */
    private Integer operType;
    
    /** 
     * 
     * operTime 操作日期
     * 
     */
    private String date;

    /** 
     * 
     * time 操作时间
     * 
     */
    private String time;
    
    /** 
     * 
     * operator 操作员
     * 
     */
    private Integer userId;
    
    /** 
     * 
     * loginName 登陆名
     * 
     */
    private String loginName;
    
    /** 
     * 
     * name 姓名
     * 
     */
    private String name;
    
    /** 
     * 
     * content 操作内容,以json格式的方式保存数据
     *
     */
    private String content;
    
    /** 
     * 
     * result 操作结果 ,成功,或失败
     * 
     */
    private Boolean result;

    /**
     * 
     * ref json to Object
     *
     */
    private Object ref;

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }
    
}
