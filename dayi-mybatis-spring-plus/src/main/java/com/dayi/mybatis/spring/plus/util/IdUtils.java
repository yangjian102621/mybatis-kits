package com.dayi.mybatis.spring.plus.util;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理所以id工具
 *
 * @author chenzhaoju
 *
 */
public final class IdUtils {
    public final static Map<Class,IdUtil> ids = new ConcurrentHashMap<>();

    /**
     * 获取一个id
     * @param clazz
     * @return
     */
    public static String getNewId(Class clazz){
        IdUtil idUtil = ids.get(clazz);
        if(null == idUtil){
            idUtil = IdUtil.getInstance();
            IdUtil tempIdUtil = ids.putIfAbsent(clazz, idUtil);
            if(null != tempIdUtil){
                idUtil = tempIdUtil;
            }
        }
        return idUtil.getNewId();
    }

    /**
     * 打印idutil
     */
    public static void printIdUtil(){
        Set<Map.Entry<Class, IdUtil>> entries = ids.entrySet();
        for (Map.Entry<Class, IdUtil> entry : entries) {
            Misc.Logger.info("{} <--> {}",entry.getKey(),entry.getValue());
        }
    }

}
