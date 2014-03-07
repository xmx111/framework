package com.ufo.framework.core.common;

/**
 * 
 * 类名称：Page 
 * 类描述：分页对象 
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 下午5:10:37 
 * @version 0.1
 *
 */
public class Page {
    
    // 默认每页记录数
    public static final int DEFAULTPAGESIZE = 20;
    
    // 总记录数
    private Integer totalRecords;
    
    // 当前页数
    private int pageIndex = 1;
    
    // 每页记录数
    private int pageSize = DEFAULTPAGESIZE;
    
    // 排序的列
    private String sort;
    
    // 排序的方向
    private String dir;
    
    // 别名
    private String alias;
    
    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    /**
     * @return 起始索引
     */
    public int getStartIndex() {
        return (this.pageIndex - 1) * pageSize;
    }

    /** 
    * @return 最后索引
    */
    public int getEndIndex() {
        int end = this.pageIndex * pageSize;
        return end > totalRecords ? totalRecords : end;
    }

    /** 
     * 总页数
     * @return
     */
    public Integer getTotalPages() {
        Integer mod = (totalRecords % pageSize);
        Integer pages = totalRecords / pageSize;
        return mod == 0 ? pages : pages + 1;
    }
}
