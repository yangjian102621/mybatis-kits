package org.rockyang.mybatis.plus.util;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理所以id工具
 *
 * @author chenzhaoju
 * @author yangjian
 *
 */
public final class IdUtils {
    private static Map<Class,IdUtil> IDS = new ConcurrentHashMap<>();

    public static String getNewId(Class clazz)
    {
        IdUtil idUtil = IDS.get(clazz);
        if(null == idUtil){
            idUtil = IdUtil.getInstance();
            IdUtil tempIdUtil = IDS.putIfAbsent(clazz, idUtil);
            if(null != tempIdUtil){
                idUtil = tempIdUtil;
            }
        }
        return idUtil.getNewId();
    }

}
