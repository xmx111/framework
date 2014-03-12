package com.ufo.framework.core.dao.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufo.framework.core.dao.IAdminDao;
import com.ufo.framework.core.entity.Admin;
import com.ufo.framework.core.security.entity.IAdmin;

/**
 * 
 * 类名称：AdminDao 
 * 类描述： 
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-12 上午9:04:20 
 * @version 0.1
 *
 */
@Repository
public class AdminDao extends BaseDao<Admin> implements IAdminDao {

    public IAdmin findByLoginName(String loginName) {
        String hql = "select t from Admin t where t.deleted =:deleted and t.loginName=:name";
        List<Admin> admins = find(hql, new String[] { "deleted", "name" }, new Object[] { Boolean.FALSE, loginName });
        if (admins.size() == 0) {
            return null;
        }
        return (IAdmin)admins.get(0);
    }

}
