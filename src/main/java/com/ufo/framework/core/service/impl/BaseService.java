package com.ufo.framework.core.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.framework.core.dto.IIdDto;
import com.ufo.framework.core.service.IBaseService;

@Transactional
public abstract class BaseService<T extends IIdDto> implements IBaseService<T> {

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
    
    
}
