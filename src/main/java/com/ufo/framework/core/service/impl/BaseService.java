package com.ufo.framework.core.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.framework.core.common.Page;
import com.ufo.framework.core.dao.IBaseDao;
import com.ufo.framework.core.dto.IIdDto;
import com.ufo.framework.core.entity.IIdEntity;
import com.ufo.framework.core.service.IBaseService;
import com.ufo.framework.core.utils.NumberUtils;

@Transactional
public abstract class BaseService<T extends IIdDto, M extends IIdEntity> implements IBaseService<T> {

    private static final String sess_oper = "session_operation";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    //    @Autowired
    //    protected IAdminDAO adminDAO;

    public BaseService() {
    }

    protected Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    //    protected IAdmin getOperation() {
    //        Object oper = RequestUtils.getAttribute(sess_oper);
    //        if (null == oper) {
    //            ManagerDTO managerDTO = (ManagerDTO) SecurityUtils.currentUser();
    //            Integer uid = null == managerDTO ? null : managerDTO.getId();
    //            if (NumberUtils.isNotEmpty(uid)) {
    //                oper = managerDAO.load(uid);
    //                RequestUtils.setAttribute(sess_oper, oper);
    //            }
    //        }
    //        return (oper instanceof Manager) ? (Manager) oper : null;
    //    }

    public T saveOrUpdate(T dto) {
        IBaseDao<M> dao = getBaseDao();
        final Number id = (Number) dto.getId();
        final boolean isNew = NumberUtils.isEmpty(id);
        M model = isNew ? getNewModel() : dao.load(id);
        if (null == model) {
            throw new ServiceException("load data failed!");
        }
        buildData(model, dto);
        if (isNew) {
            dao.create(model);
            dto.setId(model.getId());
        } else {
            dao.update(model);
        }
        return dto;
    }

    public Boolean delete(Serializable id) {
        IBaseDao<M> dao = getBaseDao();
        M model = dao.load(id);
        if (null == model) {
            return Boolean.FALSE;
        }
        dao.delete(model);
        return Boolean.TRUE;
    }

    @Transactional(readOnly = true)
    public T loadById(Serializable id) {
        IBaseDao<M> dao = getBaseDao();
        M model = dao.load(id);
        return toDto(model);
    }

    @Transactional(readOnly = true)
    public List<T> list() {
        List<M> list = getBaseDao().list();
        return toDtoList(list);
    }

    @Transactional(readOnly = true)
    public List<T> list(Page page) {
        List<M> list = getBaseDao().list(page);
        return toDtoList(list);
    }

    protected List<T> toDtoList(java.util.Collection<M> coll) {
        List<T> list = new ArrayList<T>();
        for(M model : coll){
            list.add(toDto(model));
        }
        return list;
    }

    protected abstract T toDto(M model);

    protected abstract void buildData(M model, T dto);

    protected abstract M getNewModel();
    
    protected abstract IBaseDao<M> getBaseDao();
}
