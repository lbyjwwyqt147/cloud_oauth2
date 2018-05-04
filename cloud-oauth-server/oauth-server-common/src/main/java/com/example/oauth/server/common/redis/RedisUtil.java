package com.example.oauth.server.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     *  字符串类型操作
     */
    public class Strings{
        /**
         * 将字符串value 写入缓存  如果key已存在将覆盖原有的value
         * @param key
         * @param value
         * @return
         */
        public void set(String key, Object value) {
            redisTemplate.boundValueOps(key).set(value);
        }

        /**
         *  如果键不存在则进入缓存,存在则不写入缓存也改变已经有的值。
         * @param key
         * @param value
         */
        public Boolean  setnx(String key,Object value){
            return redisTemplate.boundValueOps(key).setIfAbsent(value);
        }

        /**
         * 将字符串value 写入缓存并设置过期时间(秒为单位)
         * @param key
         * @param value
         * @param expireTime  秒
         * @return
         */
        public void set(String key, Object value, Long expireTime) {
            redisTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.SECONDS);
        }

        /**
         * 对key 设置过期时间(秒为单位)
         * @param key
         * @param expireTime  秒
         */
        public  Boolean setExpireTime(String key,Long expireTime){
            return redisTemplate.boundValueOps(key).expire(expireTime,TimeUnit.SECONDS);
        }

        /**
         * 设置key 的过期日期(日期格式)
         * @param key
         * @param date  日期
         * @return
         */
        public Boolean 	expireAt(String key, Date date){
            return redisTemplate.boundValueOps(key).expireAt(date);
        }

        /**
         * 获取key 的过期时间(秒)
         * @param key
         * @return
         */
        public Long getExpire(String key){
            return redisTemplate.boundValueOps(key).getExpire();
        }

        /**
         *  获取key 对应的值
         * @param key
         * @return
         */
        public Object get(String key){
            return redisTemplate.boundValueOps(key).get();
        }

        /**
         *  检查key 是否存在
         * @param key
         * @return
         */
        public Boolean exists(String key){
            return redisTemplate.hasKey(key);
        }

        /**
         * 在指定的key中追加value
         * @param key
         * @param value
         * @return
         */
        public  Integer append(String key,String value){
            return redisTemplate.boundValueOps(key).append(value);
        }

        /**
         * 	将key 的整数值进行+1计算
         * @param key
         * @param number
         * @return  加1后的数值
         */
        public Long incr(String key,Long number){
            return redisTemplate.boundValueOps(key).increment(number);
        }

        /**
         * 	将key 的小数值进行+1计算
         * @param key
         * @param number
         * @return  加1后的数值
         */
        public Double incr(String key,Double number){
            return redisTemplate.boundValueOps(key).increment(number);
        }
    }





}
