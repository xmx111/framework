package com.ufo.framework.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDeniedException;

import com.ufo.framework.core.common.RespBodyResult;
import com.ufo.framework.core.utils.ResponseUtils;

/**
 * 
 * 类名称：AccessDeniedHandler 
 * 类描述： 未鉴权访问、已鉴权但访问了受保护权限的自定义配置
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午1:59:36 
 * @version 0.1
 *
 */
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        String requestType = request.getHeader("X-Requested-With");
        String url = request.getRequestURI();
        final String contextPath = request.getContextPath();
        if (StringUtils.isBlank(requestType) && url.endsWith(".htm")) {
            String path = request.getRequestURI();
            request.getSession().setAttribute("message", accessDeniedException.getMessage());
            request.getSession().setAttribute("redirect", path);
            response.sendRedirect(contextPath + "/accessDenied.htm");
        } else {
            RespBodyResult result = new RespBodyResult();
            result.setStatusCode(401);
            result.setCallbackType("closeCurrent");
            result.setMessage("请添加以下权限:" + accessDeniedException.getMessage());
            ResponseUtils.write(response, result);
        }
    }

}
