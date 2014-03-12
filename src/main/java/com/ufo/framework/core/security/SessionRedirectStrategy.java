package com.ufo.framework.core.security;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;

import com.ufo.framework.core.common.RespBodyResult;
import com.ufo.framework.core.utils.ResponseUtils;

/**
 * 
 * 类名称：SessionRedirectStrategy 
 * 类描述： 会话超时
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-12 上午10:15:16 
 * @version 0.1
 *
 */
public class SessionRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        String str = request.getRequestURI();
        String[] arr = StringUtils.split(str, "/");
        String ctx = request.getContextPath();
        if (StringUtils.isNotBlank(ctx)) {
            arr = Arrays.copyOfRange(arr, 1, arr.length);
        }
        if (arr.length <= 1) {
            super.sendRedirect(request, response, url);
        } else {
            RespBodyResult result = new RespBodyResult();
            result.setMessage("会话超时,请重新登陆!");
            result.setStatusCode(301);
            ResponseUtils.write(response, result);
        }
    }
    
}
