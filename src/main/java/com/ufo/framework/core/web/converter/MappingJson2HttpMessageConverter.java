package com.ufo.framework.core.web.converter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * 类名称：MappingJson2HttpMessageConverter 
 * 类描述： 在controller里支持使用@RequestBody注解 
 * 要使用Spring提供的自动将requestbody里的json字符串的entity 转换为对应的对象实例，
 * 需要增加messageconverter，默认没有MappingJackson2HttpMessageConverter
 * ajax中文乱码，完成请求和注解POJO的映射
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-12 上午9:16:54 
 * @version 0.1
 *
 */
public class MappingJson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String jsonpcallback = ServletRequestUtils.getStringParameter(request, "jsonpcallback", null);
        String jsoncallback = ServletRequestUtils.getStringParameter(request, "jsoncallback", null);
        String callback = StringUtils.isNotBlank(jsonpcallback) ? jsonpcallback : jsoncallback;
        if (StringUtils.isNotBlank(callback)) {
            OutputStream out = outputMessage.getBody();
            out.write(callback.getBytes());
            out.write("(".getBytes());
            super.writeInternal(o, outputMessage);
            out.write(")".getBytes());
            out.flush();
        } else {
            super.writeInternal(o, outputMessage);
        }
    }
}
