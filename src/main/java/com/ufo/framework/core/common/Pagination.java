package com.ufo.framework.core.common;

import java.util.List;

/**
 * 
 * 类名称：Pagination 
 * 类描述： 分布器
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 下午5:19:54 
 * @version 0.1
 *
 */
public class Pagination<T> {
    
    private Page page;
    
    private List<T> records;
    
    public Pagination(Page page, List<T> records) {
        this.page = page;
        this.records = records;
    }
    
    public Page getPaginator() {
        return page;
    }

    public void setPaginator(Page page) {
        this.page = page;
    }
    
    public List<T> getRecords() {
        return records;
    }
    
    public void setRecords(List<T> records) {
        this.records = records;
    }
}
