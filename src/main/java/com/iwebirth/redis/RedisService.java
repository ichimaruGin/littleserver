package com.iwebirth.redis;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YY_410 on 2015/4/15.
 * redis service
 */
public class RedisService {
    @Autowired
    CommonRedisClient commonRedisClient;

    public static final String REDIS_ALIVE_TERMINAL_MAP_KEY = "TAliveId"; //在线终端数量。field对应tid，value对应time
    public static final String REDIS_INTIMEINFO_TERMINAL_MAP_KEY = "TIntimeInfo"; //实时消息。
    public static final String REDIS_RUNINFO_TERMINAL_MAP_KEY = "TRunInfo"; //实时运行参数。
    public static final String REDIS_LOCATION_TERMINAL_MAP_KEY = "TLocation"; //实时位置信息。
    public static final String REDIS_ALARM_TERMINAL_MAP_KEY = "TAlarm";//警报
    public static final String REDIS_ERROR_TERMINAL_MAP_KAY = "TError";//故障
    public static final String REDIS_OIL_TERMINAL_MAP_KEY = "TOil";//油量异常

    /**
     * 插入一组field-value
     * **/
    public void insertIntoCacheMap(String key, String field, String value){
        Map map;
        if(!commonRedisClient.hasKey(key)){
            map = new HashMap<String, String>();
            map.put(field,value);
        }else{
            map = commonRedisClient.hgetMap(key);
            map.put(field, value);
        }
        commonRedisClient.hmset(key,map);
    }

    /**
     * 删除一组field-value
     * **/
    public void removeFromCacheMap(String key, String field){
        Map map;
        if(commonRedisClient.hasKey(key)){
            map = commonRedisClient.hgetMap((key));
            if(map != null){
                map.remove(field);
                commonRedisClient.delete(key);  //这边不知道为何要先删掉key再把数据加进去，否则就没有效果，坑坑坑
                commonRedisClient.hmset(key,map);
            }
        }
    }

    /**
     * 得到map
     * **/
    public Map getMap(String key){
        return commonRedisClient.hgetMap(key);
    }

    /**
     *针对实时的信息，例如 runinfo location等，cache每个终端最新一次提交
     * update 整个map
     * **/
    public void updateCacheMap(String key, String tid, Object model){
        String cacheKey = key+"-"+tid;
        Field[] fields = model.getClass().getDeclaredFields();
        Map<String,String> map = new HashMap();
        for(Field field : fields){
            try {
                field.setAccessible(true);
                map.put(field.getName(),(String)field.get(model));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        map.put("time",System.currentTimeMillis()/1000+"");
        commonRedisClient.hmset(cacheKey, map);
    }


}
