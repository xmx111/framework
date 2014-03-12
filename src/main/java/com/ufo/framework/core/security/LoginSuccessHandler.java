package com.ufo.framework.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.ufo.framework.core.context.RuntimeContextBuilder;
import com.ufo.framework.core.context.RuntimeContextThreadLocal;
import com.ufo.framework.core.utils.LogUtils;

/**
 * 
 * 类名称：LoginSuccessHandler 
 * 类描述： 登陆成功日志记录
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午4:00:18 
 * @version 0.1
 *
 */
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("login sucess " + authentication);
        }
        RuntimeContextBuilder.build(request);
        LogUtils.addLogin(null, true);
        RuntimeContextThreadLocal.clear();
        super.onAuthenticationSuccess(request, response, authentication);
    }
    
}
