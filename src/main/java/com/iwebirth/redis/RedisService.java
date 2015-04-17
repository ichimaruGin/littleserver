package com.iwebirth.redis;

import com.iwebirth.util.StaticMap;
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
    public void updateCacheMap(String tid, Object model){
        String keyPrefix = StaticMap.typeRediskeyMap.get(model.getClass().getSimpleName());
        String cacheKey = keyPrefix+"-"+tid;
        Field[] fields = model.getClass().getDeclaredFields();
        Map<String,String> map = new HashMap();
        for(Field field : fields){
            try {
                field.setAccessible(true);
                map.put(field.getName(),String.valueOf(field.get(model)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        map.put("time",System.currentTimeMillis()/1000+"");
        commonRedisClient.hmset(cacheKey, map);
    }


}
