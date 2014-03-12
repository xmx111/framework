package com.ufo.framework.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.ufo.framework.core.context.RuntimeContextBuilder;
import com.ufo.framework.core.context.RuntimeContextThreadLocal;
import com.ufo.framework.core.utils.LogUtils;

/**
 * 
 * 类名称：LoginFailureHandler 
 * 类描述： 登陆失败日志记录
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午4:01:22 
 * @version 0.1
 *
 */
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("login failure " + exception);
        }
        RuntimeContextBuilder.build(request);
        LogUtils.addLogin(exception.getLocalizedMessage(), false);
        RuntimeContextThreadLocal.clear();
        super.onAuthenticationFailure(request, response, exception);
    }
    
}
