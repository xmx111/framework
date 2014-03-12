package com.ufo.framework.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.ufo.framework.core.common.RespBodyResult;
import com.ufo.framework.core.utils.ResponseUtils;

/**
 * 
 * 类名称：LoginUrlEntryPoint 
 * 类描述：如果访问一个受保护的url，则redirect到登录页，如果访问的不是以.htm结尾的url，则返回json
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午2:23:29 
 * @version 0.1
 *
 */
@SuppressWarnings("deprecation")
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        String requestType = request.getHeader("X-Requested-With");
        String url = request.getRequestURI();
        final String contextPath = request.getContextPath();
        if (StringUtils.isBlank(requestType) && url.endsWith(".htm")) {
            String path = request.getRequestURI();
            response.sendRedirect(contextPath + "/login.htm?redirect=" + path);
        } else {
            RespBodyResult result = new RespBodyResult();
            result.setMessage("会话超时,请重新登陆!");
            result.setStatusCode(301);
            ResponseUtils.write(response, result);
        }
    }
    
}
