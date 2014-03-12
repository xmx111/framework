package com.ufo.framework.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.framework.core.dao.IAdminDao;
import com.ufo.framework.core.security.dto.IAdminDto;
import com.ufo.framework.core.security.entity.IAdmin;
import com.ufo.framework.core.security.entity.IFunctionResource;
import com.ufo.framework.core.security.entity.IRole;

/**
 * 
 * 类名称：UserDetailsService 
 * 类描述： 用户详细信息管理：数据源、用户缓存（通过数据库管理用户、角色、权限、资源）。
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 上午11:44:37 
 * @version 0.1
 *
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    
    @Autowired
    private IAdminDao adminDao;
    
    /**
     * 
     * 重写方法 
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     * 
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 取管理员
        final IAdmin admin = adminDao.findByLoginName(username);
        if (null == admin) {
            throw new UsernameNotFoundException(username + "的用户不存在!");
        } else {
            Set<IRole> roles = admin.getRoles();
            Set<String> set = new HashSet<String>();
            for (IRole role : roles) {
                Set<IFunctionResource> functions = role.getFunctions();
                for (IFunctionResource function : functions) {
                    set.add(function.getCode());
                }
            }
            // 数据库权限转spring权限
            Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            for (String key : set) {
                authorities.add(new SimpleGrantedAuthority(key));
            }
            IAdminDto dto = admin.toIAdminDto();
            dto.setAuthorities(authorities);
            return dto;
        }
    }

}
