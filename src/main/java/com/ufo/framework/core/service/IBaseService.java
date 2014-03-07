package com.ufo.framework.core.service;

import java.io.Serializable;
import java.util.List;

import com.ufo.framework.core.common.Page;
import com.ufo.framework.core.dto.IIdDto;

public interface IBaseService<T extends IIdDto> {
    
    public T saveOrUpdate(T dto);

    public Boolean delete(Serializable id);

    public T loadById(java.io.Serializable id);

    public List<T> list();

    public List<T> list(Page page);

}
