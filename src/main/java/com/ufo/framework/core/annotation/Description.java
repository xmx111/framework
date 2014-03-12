package com.ufo.framework.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 类名称：Description 
 * 类描述： 权限自定义注解
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 上午9:49:00 
 * @version 0.1
 *
 */
@Documented
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    
    public String[] value() default "";

    public String[] code() default "";

}
