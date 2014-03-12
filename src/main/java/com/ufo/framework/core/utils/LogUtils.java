package com.ufo.framework.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.ufo.framework.core.common.SysLogType;
import com.ufo.framework.core.context.RuntimeContext;
import com.ufo.framework.core.context.RuntimeContextThreadLocal;
import com.ufo.framework.core.dto.SysLogDto;
import com.ufo.framework.core.security.dto.IAdminDto;

public class LogUtils {
    private static List<SysLogDto> cache = new ArrayList<SysLogDto>(5);

    /** 
     * 创建日志
     * @param checkinIdentity 
     * @return
     */
    public static SysLogDto buildLog(SysLogType type) {
        SysLogDto dto = new SysLogDto();
        RuntimeContext runtimeContext = RuntimeContextThreadLocal.get();
        if (null != runtimeContext) {
            dto.setClientIP(runtimeContext.getClientIp());
            dto.setRequestUrl(runtimeContext.getRequestUrl());
            Object user = runtimeContext.getUser();
            if (user instanceof IAdminDto) {
                dto.setUserId(((IAdminDto) user).getId());
            }
        }
        java.sql.Timestamp operTime = new java.sql.Timestamp(System.currentTimeMillis());
        dto.setDate(DateUtils.formatDate(operTime));
        dto.setTime(DateUtils.formatTime(operTime));
        dto.setType(type.value());
        return dto;
    }

    /** 
     * 添加登陆日志
     * @param content
     * @param result
     */
    public static void addLogin(String content, Boolean result) {
        SysLogDto log = buildLog(SysLogType.LOGIN);
        log.setContent(content);
        log.setResult(result);
        addLog(log);
    }

    
    /**
     * 
     * @param log
     * 
     */
    private static void addLog(SysLogDto log) {
        synchronized (cache) {
            cache.add(log);
        }
    }

    /**
     * 
     * @param size
     * @return
     */
    public static List<SysLogDto> pop(int size) {
        synchronized (cache) {
            size = cache.size() <= size ? cache.size() : size;
            final List<SysLogDto> subList = new ArrayList<SysLogDto>(size);
            for (int i = size - 1; i >= 0; i--) {
                subList.add(cache.remove(i));
            }
            return subList;
        }
    }
}
