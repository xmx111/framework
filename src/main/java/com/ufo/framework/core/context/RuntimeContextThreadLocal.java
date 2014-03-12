package com.ufo.framework.core.context;

/**
 * 
 * 类名称：RuntimeContextThreadLocal 
 * 类描述：  运行时访问内容线程
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午3:39:11 
 * @version 0.1
 *
 */
public class RuntimeContextThreadLocal {

    private static InheritableThreadLocal<RuntimeContext> runtimeContextThreadLocal = new InheritableThreadLocal<RuntimeContext>() {
        protected RuntimeContext initialValue() {
            return null;
        }
    };

    public static RuntimeContext create() {
        RuntimeContext context = runtimeContextThreadLocal.get();
        if (context != null) {
            return context;
        }
        context = new RuntimeContext();
        runtimeContextThreadLocal.set(context);
        return context;
    }

    public static RuntimeContext get() {
        return runtimeContextThreadLocal.get();
    }

    public static void clear() {
        runtimeContextThreadLocal.remove();
    }

}
