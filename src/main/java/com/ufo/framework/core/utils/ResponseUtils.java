package com.ufo.framework.core.utils;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 类名称：ResponseUtils 
 * 类描述： 
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午2:02:01 
 * @version 0.1
 *
 */
public class ResponseUtils {
    
    /**
     * 
     * 返回json响应
     * @param response
     * @param result
     * @throws IOException
     * 
     */
    public static void write(HttpServletResponse response, Object result) throws IOException {
        response.setHeader("Content-Type", "application/json");
        String charset = "utf-8";
        response.setCharacterEncoding(charset);
        final ServletOutputStream outputStream = response.getOutputStream();
        // 转json字符串
        final String json = JsonUtils.toJson(result);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
        writer.write(json);
        writer.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
