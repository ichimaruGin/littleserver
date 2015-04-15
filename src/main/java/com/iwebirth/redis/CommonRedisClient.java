package com.iwebirth.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;


import java.security.Key;
import java.util.*;

/**
 * Created by zjh on 14-12-18.
 * modify by yy on 2015-1-16
 */
public class CommonRedisClient extends AbstractBaseRedisDao<String, String> {

    /**
     * 下列方法直接序列化参数后再传给redis处理
     * *
     */
    public boolean add(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                String tKey = key;
                String cacheValue = value;
                byte[] key = serializer.serialize(tKey);
                byte[] value = serializer.serialize(cacheValue);
                return redisConnection.setNX(key, value);
            }
        });
        return result;
    }

    /**
     * 单键值队存储
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 单键值get
     * *
     */
    public String get(String key) {
        return get(key);
    }

    /**
     * 按键值对存入
     *
     * @param key
     * @param param
     */
    public void hmset(String key, Map<String,String> param) {
        redisTemplate.opsForHash().putAll(key, param);
    }

    /**
     * 根据map的key和fields得到对应的值
     * **/
    public Collection<String> hmget(String key, Collection<String> fields) {
        return redisTemplate.<String, String>opsForHash().multiGet(key, fields);
    }

    /**
     * 根据map的key得到map
     * **/
    public Map<Object,Object> hgetMap(String key){
        return redisTemplate.opsForHash().entries(key);
    }
    /**
     * 按照键和字段得到值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return redisTemplate.<String, String>opsForHash().get(key, field);
    }

    /**
     * 判断是否有key
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 查询匹配的key
     *
     * @param pattern
     * @return
     */
    public Set<String> getKeys(String pattern){
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除某一个键对应的记录
     *
     * @param key
     */
    public void delete(String key) {
        List<String> list = new ArrayList<String>();
        list.add(key);
        delete(list);
    }

    /**
     * 删除一系列键对应的记录
     *
     * @param keys
     */
    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 像某一个key指向的set中插入内容
     *
     * @param key
     * @param value
     * @return
     */
    public Long sadd(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取某一个key指向的set中的所有内容
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    /**
     * 清空redis缓存
     *
     * @return
     */
    public boolean deleteCurrentDB() {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return true;
            }
        });
    }


}
