package com.ufo.framework.core.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 类名称：SysLogType 
 * 类描述： 日志枚举类型
 * 
 * 
 * 创建人：khe
 * 创建时间：2014-3-11 下午3:23:29 
 * @version 0.1
 *
 */
public enum SysLogType {
    
    LOGIN, //登陆日志
    JOB, //时间机器人
    ;
    
    private static Map<SysLogType, Integer> typeMap = new HashMap<SysLogType, Integer>();
    private static Map<Integer, SysLogType> valueMap = new HashMap<Integer, SysLogType>();
    private static Map<SysLogType, String> nameMap = new HashMap<SysLogType, String>();
    static {
        typeMap.put(SysLogType.LOGIN, 1);
        typeMap.put(SysLogType.JOB, 2);
        
        nameMap.put(SysLogType.LOGIN, "登陆日志");
        nameMap.put(SysLogType.JOB, "时间机器人");
        
        for (SysLogType state : typeMap.keySet()) {
            valueMap.put(typeMap.get(state), state);
        }
    }

    public Integer value() {
        return typeMap.get(this);
    }

    public static SysLogType parse(int value) {
        if (!valueMap.containsKey(value)) {
            throw new IllegalArgumentException(value + " is not a valid SysLogType");
        }
        return valueMap.get(value);
    }

    public String toName() {
        return nameMap.get(this);
    }

    public Integer getValue() {
        return value();
    }

    public String getName() {
        return toName();
    }

    public static List<SysLogType> list() {
        final ArrayList<SysLogType> list = new ArrayList<SysLogType>(typeMap.keySet());
        Collections.sort(list, new java.util.Comparator<SysLogType>() {
            public int compare(SysLogType o1, SysLogType o2) {
                return o1.value().compareTo(o2.value());
            }

        });
        return list;
    }
}
