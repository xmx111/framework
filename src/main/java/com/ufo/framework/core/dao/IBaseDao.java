package com.ufo.framework.core.dao;

import java.io.Serializable;
import java.util.List;

import com.ufo.framework.core.common.Page;

/**
 * 
 * 类名称：IBaseDao 
 * 类描述： Dao接口
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-6 上午10:29:40 
 * @version 0.1
 *
 */
public interface IBaseDao<T> {
    
    /**
     * 
     * 返回所有数据对象
     * @param <T>
     * 
     */
    public List<T> all();
    
    /** 
     * 
     * 返回未删除的所有数据对象
     * 
     */
    public List<T> list();

    /**
     * 
     * 返回分布数据
     * @param page
     * @return
     * 
     */
    public List<T> list(Page page);

    /**
     * 
     * 返回id的对象
     * @param id
     * @return
     * 
     */
    public T load(Serializable id);

    /**
     * 
     * 持久化新对象
     * @param obj
     * @return
     * 
     */
    public Serializable create(T obj);

    /**
     * 
     * 修改对象
     * @param obj
     * 
     */
    public void update(T obj);

    /**
     * 
     * 删除对象
     * @param obj
     * 
     */
    public void delete(T obj);

}
