package com.example.oauth.server.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;


    /** #######################################   key 操作开始    #################################################################*/


    /**
     *  检查给定的key 是否存在
     *  EXISTS KEY_NAME
     *  redis 127.0.0.1:6379> EXISTS runoob-new-key
     * (integer) 1
     * @param key
     * @return key存在返回true  不存在返回false
     */
    public Boolean exists(String key){
        return redisTemplate.hasKey(key);
    }



    /**
     *  删除指定key(单个key)
     *  DEL KEY_NAME
     *  redis 127.0.0.1:6379> DEL w3ckey
     * (integer) 1
     * @param key
     * @return 删除成功返回true  失败返回false
     */
    public Boolean del(String key){
        if (exists(key)){
            return  redisTemplate.delete(key);
        }
        return false;
    }

    /**
     * 删除指定多个key(批量删除多个key)
     * @param keys
     * @return 成功返回true  失败返回false
     */
    public Boolean deletes(Set<String> keys){
        Long count = delete(keys);
        if (count != null && count > 0){
            return true;
        }
        return false;
    }

    /**
     * 删除指定多个key(批量删除多个key)
     * @param keys
     * @return 返回 删除key个数
     */
    public Long delete(Set<String> keys){
        return redisTemplate.delete(keys);
    }

    /**
     *  模糊删除 支持*号等匹配删除
     * @param pattern
     */
    public Boolean deleteBlear(String pattern){
        Set<String> keys = this.keys(pattern);
        return deletes(keys);
    }

    /**
     *  清空所有key
     * @return
     */
    public Boolean clearAllKeys(){
        return deletes(getAllKeys());
    }



    /**
     * 修改key名 如果不存在该key或者没有修改成功返回false
     *  RENAMENX OLD_KEY_NAME NEW_KEY_NAME
     * redis> RENAMENX player best_player
     * (integer) 1
     *
     * @param oldKey 被修改的key
     * @param newKey 替换后的key
     * @return  修改成功返回true  失败返回false
     */
   public Boolean renamenx(String oldKey,String newKey){
        return  redisTemplate.renameIfAbsent(oldKey,newKey);
   }

    /**
     * 查找所有匹配给定的模式的键
     * KEYS PATTERN
     * redis 127.0.0.1:6379> KEYS runoob*
     * 1) "runoob3"
     * 2) "runoob1"
     * 3) "runoob2"
     * @param pattern  String  key的表达式,*表示多个，？表示一个
     * @return
     */
    public Set<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }

    /**
     * 获取全部key
     * @return 返回 key 集合
     */
    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }

    /**
     * 处理事物锁定的key
     * 用于监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断
     * WATCH key [key ...]
     * redis> WATCH lock lock_times
     * OK
     * @param key
     */
    public void watch(String key) {
        redisTemplate.watch(key);
    }


    /** #######################################   key 操作结束    ################################################################# */


    /** ######   字符串类型操作开始   对整个字符串或者字符串的其中一部分执行操作；对象和浮点数执行自增(increment)或者自减(decrement)  ############# */

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
     * 指定的字符串覆盖给定 key 所储存的字符串值，覆盖的位置从偏移量 offset 开始
     * SETRANGE KEY_NAME OFFSET VALUE
     *
     * redis 127.0.0.1:6379> SET key1 "Hello World"
     * OK
     * redis 127.0.0.1:6379> SETRANGE key1 6 "Redis"
     * (integer) 11
     * redis 127.0.0.1:6379> GET key1
     * "Hello Redis"
     *
     * @param key
     * @param value
     * @param offset
     */
    public void setrange(String key,Object value,Long offset){
         redisTemplate.boundValueOps(key).set(value,offset);
    }

    /**
     *  如果键不存在则存入缓存,存在则不写入缓存也改变已经有的值。
     *  SETNX KEY_NAME VALUE
     *  redis> EXISTS job                # job 不存在
     * (integer) 0
     *
     * redis> SETNX job "programmer"    # job 设置成功
     * (integer) 1
     *
     * redis> SETNX job "code-farmer"   # 尝试覆盖 job ，失败
     * (integer) 0
     *
     * redis> GET job                   # 没有被覆盖
     * "programmer"
     *
     * @param key
     * @param value  设置成功返回true  设置失败返回 false
     */
    public Boolean  setnx(String key,Object value){
        return redisTemplate.boundValueOps(key).setIfAbsent(value);
    }

    /**
     * 将字符串value 写入缓存并设置过期时间(秒为单位)
     * 如果 key 已经存在， SETEX 命令将会替换旧的值
     * SETEX KEY_NAME TIMEOUT VALUE
     * redis 127.0.0.1:6379> SETEX mykey 60 redis
     * OK
     * redis 127.0.0.1:6379> TTL mykey
     * 60
     * redis 127.0.0.1:6379> GET mykey
     * "redis
     * @param key
     * @param value
     * @param expireTime  秒
     * @return
     */
    public void setex (String key, Object value, Long expireTime) {
        redisTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.SECONDS);
    }

    /**
     *  获取key 对应的值
     *  GET KEY_NAME
     *  redis> GET db
     * "redis"
     *
     * @param key
     * @return  返回key对应的值
     */
    public Object get(String key){
        return redisTemplate.boundValueOps(key).get();
    }


    /**
     * 在指定的key中追加value
     * APPEND KEY_NAME NEW_VALUE
     * redis> APPEND myphone "nokia"       # 对不存在的 key 进行 APPEND ，等同于 SET myphone "nokia"
     * (integer) 5                         # 字符长度
     *
     *
     * # 对已存在的字符串进行 APPEND
     *
     * redis> APPEND myphone " - 1110"     # 长度从 5 个字符增加到 12 个字符
     * (integer) 12
     *
     * redis> GET myphone
     * "nokia - 1110"
     *
     * @param key
     * @param value
     * @return 返回追加指定值之后， key 中字符串的长度。
     */
    public  Integer append(String key,String value){
        return redisTemplate.boundValueOps(key).append(value);
    }



    /**
     * 	将 key 中储存的数字值增一 计算
     * 	 INCR KEY_NAME
     * 	 redis> SET page_view 20
     * OK
     *
     * redis> INCR page_view
     * (integer) 21
     *
     * redis> GET page_view    # 数字值在 Redis 中以字符串的形式保存
     * "21"
     * @param key
     * @return  返回值 增一后的数值
     */
   /* public Long incr(String key){
        return redisTemplate.boundValueOps(key).increment();
    }*/

    /**
     * 	将key 的整数值进行加法计算 如果 key 不存在，那么 key 的值会先被初始化为
     * 	INCRBY KEY_NAME INCR_AMOUNT
     * 	redis> SET rank 50
     * OK
     *
     * redis> INCRBY rank 20
     * (integer) 70
     *
     * redis> GET rank
     * "70"
     *
     *
     * # key 不存在时
     *
     * redis> EXISTS counter
     * (integer) 0
     *
     * redis> INCRBY counter 30
     * (integer) 30
     *
     * redis> GET counter
     * "30"
     * @param key
     * @param number  加数值
     * @return  返回值 加法后的long数值
     */
    public Long incrby (String key,Long number){
        return redisTemplate.boundValueOps(key).increment(number);
    }

    /**
     * 	将key 的小数值进行加法计算  如果 key 不存在，那么 key 的值会先被初始化为
     * @param key
     * @param number 加数值
     * @return  返回值加法计算后的double数值
     */
    public Double incrby(String key,Double number){
        return redisTemplate.boundValueOps(key).increment(number);
    }




    /**
     *  将 key 中储存的数字值减一
     *  如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作
     *  DECR KEY_NAME
     *redis> SET failure_times 10
     * OK
     *
     * redis> DECR failure_times
     * (integer) 9
     *
     * @param key
     * @return 返回会减一后的数值
     */
    /*public Long decr(String key){
        return redisTemplate.boundValueOps(key).decrement();
    }*/



    /**
     * 	将key 的整数值进行减法计算 如果 key 不存在，那么 key 的值会先被初始化为
     * DECRBY KEY_NAME DECREMENT_AMOUNT
     * @param key
     * @param number  减数值
     * @return  返回值 减法后的long数值
     */
   /* public Long decrby (String key,Long number){
        return redisTemplate.boundValueOps(key).decrement(number);
    }*/


    /**
     * 指定key 的字符串值  并且返回key的旧值
     * GETSET KEY_NAME VALUE
     * redis> GETSET db mongodb    # 没有旧值，返回 nil
     * (nil)
     *
     * redis> GET db
     * "mongodb"
     *
     * redis> GETSET db redis      # 返回旧值 mongodb
     * "mongodb"
     *
     * redis> GET db
     * "redis"
     * @param key
     * @param value
     * @return 返回一个字符串，也就是键的旧值。 如果键不存在，则返回null
     */
    public Object getset (String key,Object value){
        return redisTemplate.boundValueOps(key).getAndSet(value);
    }

    /**
     * 对key 设置过期时间(秒为单位)  key 过期后将不再可用，会自动删除
     * Expire KEY_NAME TIME_IN_SECONDS
     * redis 127.0.0.1:6379> EXPIRE runooobkey 60
     * (integer) 1
     * @param key
     * @param expireTime  秒
     * @return 设置成功返回true  失败返回false
     */
    public  Boolean expire(String key,Long expireTime){
        return redisTemplate.boundValueOps(key).expire(expireTime,TimeUnit.SECONDS);
    }


    /**
     * 用于以 UNIX 时间戳(unix timestamp)格式设置 key 的过期时间。key 过期后将不再可用
     * Expireat KEY_NAME TIME_IN_UNIX_TIMESTAMP
     * redis 127.0.0.1:6379> EXPIREAT runoobkey 1293840000
     * (integer) 1
     * @param key
     * @param date  时间
     * @return 设置成功返回true  失败返回false
     */
    public Boolean 	expireAt(String key, Date date){
        return redisTemplate.boundValueOps(key).expireAt(date);
    }


    /**
     * 获取key 的过期时间(秒) 以秒为单位返回 key 的剩余过期时间
     *
     * 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
     * @param key
     * @return  返回key 的过期时间(秒)
     */
    public Long ttl(String key){
        return redisTemplate.boundValueOps(key).getExpire();
    }


    /**
     * 修改 key 的名称
     * RENAME OLD_KEY_NAME NEW_KEY_NAME
     *
     * redis> SET message "hello world"
     * OK
     *
     * redis> RENAME message greeting
     * OK
     *
     * redis> EXISTS message               # message 不复存在
     * (integer) 0
     *
     * redis> EXISTS greeting              # greeting 取而代之
     * (integer) 1
     * @param oldKey 被修改的key
     * @param newKey 替换后的key
     */
    public void  rename(String oldKey,String newKey) {
        redisTemplate.boundValueOps(oldKey).rename(newKey);
    }

    /**
     *  获取key 对应值的长度
     *  STRLEN KEY_NAME
     *  redis> STRLEN mykey
     * (integer) 11
     * @param key
     * @return 返回key 对应值的长度  不存在返回0
     */
    public Long strlen(String key){
        return redisTemplate.boundValueOps(key).size();
    }

    /**
     * 取消对key过期时间的设置(移除过期时间后 key 永不过期)
     * PERSIST KEY_NAME
     *
     * redis> PERSIST mykey    # 移除 key 的生存时间
     * (integer) 1
     * @param key
     * @return 成功返回true 失败返回false
     */
    public Boolean persist(String key){
        return redisTemplate.boundValueOps(key).persist();
    }

    /**
     * 获取指定key存储的类型
     *  key 的数据类型，数据类型有：
     * none (key不存在)
     * string (字符串)
     * list (列表)
     * set (集合)
     * zset (有序集)
     * hash (哈希表)
     *
     * TYPE KEY_NAME
     * redis> TYPE weather
     * string
     * @param key
     * @return 返回指定key存储的类型 String |list|set|zset|hash
     */
    public DataType getType(String key) {
        return  redisTemplate.boundValueOps(key).getType();
    }

    /** #######################################   字符串类型操作结束    ################################################################# */







    /** #########   Set 类型操作开始  添加、获取、移除单个元素；检查一个元素是否存在于某个集合中；计算交集、并集、差集；从集合里卖弄随机获取元素   ##################### */


    /**
     * 将一个或多个成员元素加入到set集合中，已经存在于集合的成员元素将被忽略。
     * 假如集合 key 不存在，则创建一个只包含添加的元素作成员的集合。
     * @param key
     * @param values
     * @return  返回被添加到集合中的新元素的数量，不包括被忽略的元素。
     */
    public Long  sadd(String key,Object... values){
        return redisTemplate.boundSetOps(key).add(values);
    }

    /**
     *  根据Key 获取set集合中成员数据
     * @param key
     * @return 返回指定key 的set 集合数据
     */
    public Set<Object> smembers(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    /**
     *  返回给定集合之间的差集。不存在的集合 key 将视为空集 (多组集合之间)
     * @param key
     * @param set
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * SDIFF key1 key2 key3 = {b,d}
     * @return  返回给定集合之间的差集。不存在的集合 key 将视为空集
     */
    public Set<Object> sdiff(String key,Set<Object> set){
        return redisTemplate.boundSetOps(key).diff(set);
    }

    /**
     *  返回给定集合之间的差集。不存在的集合 key 将视为空集（两组集合之间）
     *  SDIFF key1 key2
     * @param key
     * @param key 1
     * @return 返回给定集合之间的差集。不存在的集合 key 将视为空集
     */
    public Set<Object> sidff(String key,String key1){
        return redisTemplate.boundSetOps(key).diff(key1);
    }

    /**
     *  从set集合中移除 并返回集合中的一个随机元素。
     *  SPOP KEY
     *  redis 127.0.0.1:6379> SADD myset1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "world"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> SPOP myset1
     * "bar"
     * redis 127.0.0.1:6379> SMEMBERS myset1
     *  "Hello"
     *  "world"
     * @param key
     * @return 返回被移除的随机元素。 当集合不存在或是空集时，返回 nil
     */
    public  Object spop(String key){
        return redisTemplate.boundSetOps(key).pop();
    }

    /**
     * 从set 集合中移除集合中的一个或多个成员元素，不存在的成员元素会被忽略。
     *  SREM KEY MEMBER1..MEMBERN
     *  redis 127.0.0.1:6379> SADD myset1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "world"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> SREM myset1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SREM myset1 "foo"
     * (integer) 0
     * redis 127.0.0.1:6379> SMEMBERS myset1
     *  "bar"
     * 　"world"
     * @param key
     * @param objects  要移除的元素
     * @return 被成功移除的元素的数量，不包括被忽略的元素。
     */
    public Long srem(String key,Object... objects){
        return redisTemplate.boundSetOps(key).remove(objects);
    }


    /**
     * 根据key 获取 set 中的集合大小
     * SCARD KEY_NAME
     * redis 127.0.0.1:6379> SADD myset "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset "hello"
     * (integer) 0
     * redis 127.0.0.1:6379> SCARD myset
     * (integer) 2
     * @param key
     * @return 返回 集合大小
     */
    public Long scard(String key){
        return redisTemplate.boundSetOps(key).size();
    }

    /**
     * 根据key 确定一个给定的值是否存在
     * SISMEMBER KEY VALUE
     * redis 127.0.0.1:6379> SADD myset1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SISMEMBER myset1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SISMEMBER myset1 "world"
     * (integer) 0
     * @param key
     * @param value  需要判断的值
     * @return  值存在返回true  不存在返回false
     */
    public Boolean sismember(String key,Object value){
        return redisTemplate.boundSetOps(key).isMember(value);
    }

    /**
     *  （移动元素）将指定成员 value 元素从 source 集合移动到 destination 集合
     *  如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。
     *  当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除
     *  SMOVE SOURCE DESTINATION MEMBER
     *  redis 127.0.0.1:6379> SADD myset1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "world"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset2 "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> SMOVE myset1 myset2 "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> SMEMBERS myset1
     *  "World"
     *  "Hello"
     * redis 127.0.0.1:6379> SMEMBERS myset2
     *  "foo"
     *  "bar"
     * @param srckey  源数据 key
     * @param dstkey  需要将数据移动到目标set集合中的 key
     * @param value   需要移动的数据值
     * @return 成功返回true 失败返回false
     */
    public Boolean smove(String srckey, String dstkey, Object value) {
        return redisTemplate.boundSetOps(srckey).move(dstkey,value);
    }

    /**
     * （获取并集） 返回给定集合的并集。不存在的集合 key 被视为空集。（两组集合之间）
     *  SUNION KEY KEY1..KEYN
     *  redis> SADD key1 "a"
     * (integer) 1
     * redis> SADD key1 "b"
     * (integer) 1
     * redis> SADD key1 "c"
     * (integer) 1
     * redis> SADD key2 "c"
     * (integer) 1
     * redis> SADD key2 "d"
     * (integer) 1
     * redis> SADD key2 "e"
     * (integer) 1
     * redis> SUNION key1 key2
     *  "a"
     *  "c"
     *  "b"
     *  e"
     *  "d"
     * redis>
     * @param key
     * @param otherKey
     * @return 返回 并集成员的列表
     */
    public Set<Object> sunion(String key,String otherKey){
        return redisTemplate.boundSetOps(key).union(otherKey);
    }

    /**
     *  （获取并集）返回给定集合的并集。不存在的集合 key 被视为空集。（多组集合之间）
     *  SUNION KEY KEY1..KEYN
     * @param key
     * @param set
     * @return 返回 并集成员的列表
     */
    public Set<Object> sunion(String key, Set<Object> set){
        return redisTemplate.boundSetOps(key).union(set);
    }

    /**
     * （并集）将给定集合的并集存储在指定的集合 destination 中。如果 destination 已经存在，则将其覆盖
     * SUNIONSTORE DESTINATION KEY KEY1..KEYN
     * redis 127.0.0.1:6379> SADD myset1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "world"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset2 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset2 "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> SUNIONSTORE myset myset1 myset2
     * (integer) 1
     * redis 127.0.0.1:6379> SMEMBERS myset
     *  "bar"
     *  "world"
     *  "hello"
     *  "foo"
     * @param key          并集存储的目标key
     * @param otherKey     给定的源集合 key
     * @param destKey     给定的源集合key
     * @return
     */
    public  void  sunionstore(String key, String otherKey, String destKey){
        redisTemplate.boundSetOps(key).unionAndStore(otherKey,destKey);
    }


    /**
     * （并集）将给定集合的并集存储在指定的集合 destination 中。如果 destination 已经存在，则将其覆盖
     * SUNIONSTORE DESTINATION KEY KEY1..KEYN
     * @param key          并集存储的目标key
     * @param otherKey
     * @param set     给定的源集合 key
     * @return
     */
    public  void  sunionstore(String key,String otherKey ,Set<Object> set){
        redisTemplate.boundSetOps(key).unionAndStore(set,otherKey);
    }


    /**
     *  （获取交集）返回给定集合的交集。不存在的集合 key 被视为空集。（两组集合之间）
     *  SINTER KEY KEY1..KEYN
     *  redis 127.0.0.1:6379> SADD myset "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset2 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset2 "world"
     * (integer) 1
     * redis 127.0.0.1:6379> SINTER myset myset2
     *  "hello"
     * @param key
     * @param otherKey
     * @return 返回 交集成员的列表
     */
    public Set<Object> sinter(String key,String otherKey){
        return redisTemplate.boundSetOps(key).intersect(otherKey);
    }

    /**
     * （获取交集） 返回给定集合的交集。不存在的集合 key 被视为空集。（多组集合之间）
     *  SINTER KEY KEY1..KEYN
     * @param key
     * @param set
     * @return 返回 交集成员的列表
     */
    public Set<Object> sinter(String key, Set<Object> set){
        return redisTemplate.boundSetOps(key).intersect(set);
    }

    /**
     * （交集）将给定集合的交集存储在指定的集合 destination 中。如果 destination 已经存在，则将其覆盖
     * SINTERSTORE DESTINATION_KEY KEY KEY1..KEYN
     * redis 127.0.0.1:6379> SADD myset1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset1 "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset2 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> SADD myset2 "world"
     * (integer) 1
     * redis 127.0.0.1:6379> SINTERSTORE myset myset1 myset2
     * (integer) 1
     * redis 127.0.0.1:6379> SMEMBERS myset
     * 1) "hello"
     * @param key          交集存储的目标key
     * @param otherKey     给定的源集合 key
     * @param destKey     给定的源集合key
     * @return
     */
    public  void  sinterstore(String key, String otherKey, String destKey){
        redisTemplate.boundSetOps(key).intersectAndStore(otherKey,destKey);
    }


    /**
     *（交集）将给定集合的交集存储在指定的集合 destination 中。如果 destination 已经存在，则将其覆盖
     * SINTERSTORE DESTINATION_KEY KEY KEY1..KEYN
     * @param key          交集存储的目标key
     * @param otherKey
     * @param set     给定的源集合 key
     * @return
     */
    public  void  sinterstore(String key,String otherKey ,Set<Object> set){
        redisTemplate.boundSetOps(key).intersectAndStore(set,otherKey);
    }

    /**
     * 设置set 过期时间(秒)
     * 设置 key 的过期时间。key 过期后将不再可用
     *  Expire KEY_NAME TIME_IN_SECONDS
     * redis 127.0.0.1:6379> EXPIRE runooobkey 60
     * (integer) 1
     *
     * @param key
     * @param time  秒
     * @return 成功返回true  失败返回false
     */
    public Boolean setSetExpireTime(String key, Long time) {
        return redisTemplate.boundSetOps(key).expire(time, TimeUnit.SECONDS);
    }


    /** #######################################   Set 类型操作结束    ################################################################# */





    /** ################   Zset 类型操作开始  添加、获取、删除单个元素；根据分值范围(range)或者成员来获取元素   ################################## */

    /**
     *  将一个或多个成员元素及其分数值加入到有序集当中。
     *  如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。
     *  分数值可以是整数值或双精度浮点数。
     *  如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     *  ZADD KEY_NAME SCORE1 VALUE1.. SCOREN VALUEN
     *  redis> ZADD myzset 1 "one"
     * (integer) 1
     * redis> ZADD myzset 1 "uno"
     * (integer) 1
     * redis> ZADD myzset 2 "two" 3 "three"
     * (integer) 2
     * redis> ZRANGE myzset 0 -1 WITHSCORES
     * 1) "one"
     * 2) "1"
     * 3) "uno"
     * 4) "1"
     * 5) "two"
     * 6) "2"
     * 7) "three"
     * 8) "3"
     * redis>
     * @param key
     * @param score
     * @param value
     * @return 成功返回true  失败返回false
     */
    public Boolean zadd(String key, double score, Object value){
        return redisTemplate.boundZSetOps(key).add(value,score);
    }

    /**
     * 给指定key 上添加元组，或者在已经存在的情况下更新它的分数。
     * 将一个或多个成员元素及其分数值加入到有序集当中。
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素
     * 分数值可以是整数值或双精度浮点数。
     * ZADD KEY_NAME SCORE1 VALUE1.. SCOREN VALUEN
     * ZADD myzset 2 "two" 3 "three"
     * @param key
     * @param value
     * @return 返回被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
     */
    public Long zadd(String key, Set<Object> value) {
        return redisTemplate.boundZSetOps(key).add(value);
    }


    /**
     * 获取集合中元素的数量
     * ZCARD KEY_NAME
     * redis> ZADD myzset 1 "one"
     * (integer) 1
     * redis> ZADD myzset 2 "two"
     * (integer) 1
     * redis> ZCARD myzset
     * (integer) 2
     * redis>
     * @param key
     * @return 如果返回0则集合不存在
     */
    public Long zcard(String key){
        return redisTemplate.boundZSetOps(key).zCard();
    }

    /**
     * 获取指定区间内集合的数量
     * ZCOUNT key min max
     * ZCOUNT myzset 1 3    # 得到分值 从1 到 3 之间 有多少个集合数量
     * redis 127.0.0.1:6379> ZADD myzset 1 "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> ZADD myzset 1 "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> ZADD myzset 2 "world" 3 "bar"
     * (integer) 2
     * redis 127.0.0.1:6379> ZCOUNT myzset 1 3
     * (integer) 4
     * @param key
     * @param min  最小值（包含）
     * @param max  最大值（包含）
     * @return 分数值在 min 和 max 之间的成员的数量。
     */
    public Long zcount(String key, double min, double max){
        return redisTemplate.boundZSetOps(key).count(min,max);
    }

    /**
     * 获取 绑定key存储的排序集的元素个数
     * @param key
     * @return 返回给定绑定键存储的排序集的元素个数。
     */
    public Long getZSetSize(String  key){
        return redisTemplate.boundZSetOps(key).size();
    }

    /**
     * 设置 zset 过期时间(秒)
     * 设置 key 的过期时间。key 过期后将不再可用
     *  Expire KEY_NAME TIME_IN_SECONDS
     * redis 127.0.0.1:6379> EXPIRE runooobkey 60
     * (integer) 1
     *
     * @param key
     * @param time  秒
     * @return 成功返回true  失败返回false
     */
    public Boolean setZSetExpireTime(String key, Long time) {
        return redisTemplate.boundZSetOps(key).expire(time, TimeUnit.SECONDS);
    }

    /**
     * 获取键为K的集合，value为obj的元素分数
     * ZSCORE key member
     * ZSCORE salary peter      得到key为salary 成员为peter 的分数值
     * redis 127.0.0.1:6379> ZRANGE salary 0 -1 WITHSCORES    # 测试数据
     *  "tom"
     *  "2000"
     *  "peter"
     *  "3500"
     *  "jack"
     *  "5000"
     *
     * redis 127.0.0.1:6379> ZSCORE salary peter              # 注意返回值是字符串
     * "3500"
     * @param key
     * @param value
     * @return 返回成员分数值
     */
    public Double zscore(String key, Object value) {
        return redisTemplate.boundZSetOps(key).score(value);
    }

    /**
     *  移除有序集中，指定分数（score）区间内的所有成员
     *  ZREMRANGEBYSCORE key min max
     *  ZREMRANGEBYSCORE salary 1500 3500      # 移除所有薪水在 1500 到 3500 内的员工
     *  redis 127.0.0.1:6379> ZRANGE salary 0 -1 WITHSCORES          # 显示有序集内所有成员及其 score 值
     *  "tom"
     *  "2000"
     *  "peter"
     *  "3500"
     *  "jack"
     *  "5000"
     *
     * redis 127.0.0.1:6379> ZREMRANGEBYSCORE salary 1500 3500      # 移除所有薪水在 1500 到 3500 内的员工
     * (integer) 2
     *
     * redis> ZRANGE salary 0 -1 WITHSCORES          # 剩下的有序集成员
     *  "jack"
     *  "5000"
     * @param key
     * @param min 最小值(包含)
     * @param max 最大值(包含)
     */
    public void zremrangeByScore(String key,Double min,Double max){
         redisTemplate.boundZSetOps(key).removeRangeByScore(min,max);
    }

    /**
     *  移除有序集中，索引start<=index<=end 区间内的所有成员
     *  ZREMRANGEBYSCORE key min max
     *  ZREMRANGEBYSCORE salary 1500 3500      # 移除所有薪水在 1500 到 3500 内的员工
     * @param key
     * @param start  开始索引
     * @param end  结束索引
     */
    public void   zremrangeByRank(String key,Long start, Long end){
        redisTemplate.boundZSetOps(key).removeRange(start,end);
    }


    /**
     * 移除key 对应的value (多组)
     * 移除有序集中的一个或多个成员，不存在的成员将被忽略
     * ZREM key member [member ...]
     *
     * redis 127.0.0.1:6379> ZREM page_rank google.com   # 移除单个元素
     * (integer) 1
     *
     *
     * redis 127.0.0.1:6379> ZREM page_rank baidu.com bing.com   # 移除多个元素
     * (integer) 2
     *
     *
     * redis 127.0.0.1:6379> ZREM page_rank non-exists-element  # 移除不存在元素
     * (integer) 0
     *
     * @param key
     * @param value
     * @return  被成功移除的成员的数量
     */
    public Long zrem(String key, Object... value) {
        return redisTemplate.boundZSetOps(key).remove(value);
    }

    /**
     *  根据key移除  zset 集合
     * @param key
     */
    public void zrem(String key) {
        zremrangeByRank(key, 0L, getZSetSize(key));
    }

    /**
     * 并集 将otherKey对应的集合和destKey对应的集合合并到key中
     * ZINTERSTORE destination numkeys key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]
     * redis > ZADD mid_test 70 "Li Lei"
     * (integer) 1
     * redis > ZADD mid_test 70 "Han Meimei"
     * (integer) 1
     * redis > ZADD mid_test 99.5 "Tom"
     * (integer) 1
     *
     * redis > ZADD fin_test 88 "Li Lei"
     * (integer) 1
     * redis > ZADD fin_test 75 "Han Meimei"
     * (integer) 1
     * redis > ZADD fin_test 99.5 "Tom"
     * (integer) 1
     *
     * redis > ZINTERSTORE sum_point 2 mid_test fin_test
     * (integer) 3
     *
     * redis > ZRANGE sum_point 0 -1 WITHSCORES     # 显示有序集内所有成员及其 score 值
     * 1) "Han Meimei"
     * 2) "145"
     * 3) "Li Lei"
     * 4) "158"
     * 5) "Tom"
     * 6) "199"
     * @param key         目标key
     * @param otherKey    源数据key
     * @param destKey     源数据key
     */
    public void zunionstore(String key,String otherKey, String destKey){
        redisTemplate.boundZSetOps(key).unionAndStore(otherKey,destKey);
    }

    /**
     *  并集 将otherKeys对应的集合和destKey对应的集合合并到key中
     * @param key
     * @param otherKeys
     * @param destKey
     */
   public void zunionstore(String key,Set<String> otherKeys, String destKey){
        redisTemplate.boundZSetOps(key).unionAndStore(otherKeys,destKey);
   }

    /**
     *  对指定key 集合进行倒序排序
     * redis 127.0.0.1:6379> ZREVRANGE salary 0 -1 WITHSCORES     # 递减排列
     *  "jack"
     *  "5000"
     *  "tom"
     *  "4000"
     *  "peter"
     *  "3500"
     * @param key
     * @return 返回排序后的集合
     */
   public Set<Object> zrevrange(String key){
       return redisTemplate.boundZSetOps(key).reverseRange(0L,getZSetSize(key));
   }

    /**
     *  对指定key 集合进行升序排序
     * redis 127.0.0.1:6379> ZRANGE salary 0 -1 WITHSCORES        # 递增排列
     *  "peter"
     *  "3500"
     *  "tom"
     *  "4000"
     *  "jack"
     *  "5000"
     * @param key
     * @return 返回排序后的集合
     */
    public Set<Object> zrange(String key){
        return redisTemplate.boundZSetOps(key).range(0L,getZSetSize(key));
    }

    /**
     * 对指定key 集合 区间 进行升序排序
     * redis 127.0.0.1:6379> ZRANGE salary 0 -1 WITHSCORES        # 递增排列
     *  "peter"
     *  "3500"
     *  "tom"
     *  "4000"
     *  "jack"
     *  "5000"
     * @param key
     * @param start 开始索引（包含）
     * @param end  结束索引（包含）
     * @return 返回排序后的集合
     */
    public Set<Object> zrange(String key,Long start,Long end){
        return redisTemplate.boundZSetOps(key).range(start,end);
    }

    /**
     *  对指定key 集合 区间进行倒序排序
     * redis 127.0.0.1:6379> ZREVRANGE salary 0 -1 WITHSCORES     # 递减排列
     *  "jack"
     *  "5000"
     *  "tom"
     *  "4000"
     *  "peter"
     *  "3500"
     * @param key
     * @param start 开始索引（包含）
     * @param end  结束索引（包含）
     * @return 返回排序后的集合
     */
    public Set<Object> zrevrange(String key,Long start,Long end){
        return redisTemplate.boundZSetOps(key).reverseRange(start,end);
    }

    /**
     * 返回指定key有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列(s升序)。
     * ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]
     * redis 127.0.0.1:6379> ZRANGEBYSCORE salary (5000 400000            # 显示工资大于 5000 小于等于 400000 的成员
     *  "peter"
     * @param key
     * @param min 最小分值(包含)
     * @param max 最小分值(包含)
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
     */
    public Set<Object> zrangeByScore(String key,Double min,Double max){
        return redisTemplate.boundZSetOps(key).rangeByScore(min,max);
    }

    /**
     * 返回有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列(降序)。
     * ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]
     * redis 127.0.0.1:6379> ZREVRANGEBYSCORE salary 10000 2000  # 逆序排列薪水介于 10000 和 2000 之间的成员
     *  "peter"
     *  "tom"
     *  "joe"
     * @param key
     * @param min 最小分值(包含)
     * @param max 最小分值(包含)
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
     */
    public Set<Object> zrevrangeByScore(String key,Double min,Double max){
        return redisTemplate.boundZSetOps(key).reverseRangeByScore(min,max);
    }

    /**
     * 键为K的集合，索引start<=index<=end的元素子集
     * 返回泛型接口（包括score和value），正序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> getZSetRangeWithScores(String key, Long start, Long end) {
        return redisTemplate.boundZSetOps(key).rangeWithScores(start, end);
    }
    /**
     * 键为K的集合，索引start<=index<=end的元素子集
     * 返回泛型接口（包括score和value），倒序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> getZSetReverseRangeWithScores(String key, Long start, Long end) {
        return redisTemplate.boundZSetOps(key).reverseRangeWithScores(start, end);
    }

    /**
     * 键为K的集合
     * 返回泛型接口（包括score和value），升序
     * @param key
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> getZSetRangeWithScores(String key) {
        return getZSetRangeWithScores(key, 0L, getZSetSize(key));
    }

    /**
     * 键为K的集合
     * 返回泛型接口（包括score和value），倒序
     * @param key
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> getZSetReverseRangeWithScores(String key) {
        return getZSetReverseRangeWithScores(key, 0L, getZSetSize(key));
    }

    /**
     * 对有序集合中指定成员的分数加上增量 increment
     * 可以通过传递一个负数值 increment ，让分数减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
     *  ZINCRBY key increment member
     *  redis> ZADD myzset 1 "one"
     * (integer) 1
     * redis> ZADD myzset 2 "two"
     * (integer) 1
     * redis> ZINCRBY myzset 2 "one"
     * "3"
     * redis> ZRANGE myzset 0 -1 WITHSCORES
     * 1) "two"
     * 2) "2"
     * 3) "one"
     * 4) "3"
     * @param key
     * @param value    需要增量的元素值
     * @param delta    增量数值
     * @return  成员的新分数值
     */
    public double zincrby (String key, Object value, double delta) {
        return redisTemplate.boundZSetOps(key).incrementScore(value, delta);
    }


    /**
     * 获取指定值在集合中的位置，集合排序从低到高
     *  ZRANK key member
     *  redis 127.0.0.1:6379> ZRANGE salary 0 -1 WITHSCORES        # 显示所有成员及其 score 值
     *  "peter"
     *  "3500"
     *  "tom"
     *  "4000"
     *  "jack"
     *  "5000"
     *
     * redis 127.0.0.1:6379> ZRANK salary tom                     # 显示 tom 的薪水排名，第二
     * (integer) 1
     * @param key
     * @param vlaue
     * @return 值在集合中的索引
     */
    public Long zrank(String key,Object vlaue){
        return redisTemplate.boundZSetOps(key).rank(vlaue);
    }

    /**
     * 获取指定值在集合中的位置，集合排序从高到低
     * ZREVRANK key member
     * redis 127.0.0.1:6379> ZRANGE salary 0 -1 WITHSCORES     # 测试数据
     *  "jack"
     *  "2000"
     *  "peter"
     *  "3500"
     *  "tom"
     *  "5000"
     *
     * redis 127.0.0.1:6379> ZREVRANK salary peter     # peter 的工资排第二
     * (integer) 1
     *
     * redis 127.0.0.1:6379> ZREVRANK salary tom       # tom 的工资最高
     * (integer) 0
     * @param key
     * @param value
     * @return 值在集合中的索引位置
     */
    public Long zrevrank(String key,Object value){
        return redisTemplate.boundZSetOps(key).reverseRank(value);
    }

    /** #######################################   ZSet 类型操作结束    ################################################################# */



    /** ##############  List 类型操作开始 　从链表的两端推入或者弹出元素；根据偏移量对链表进行修剪(trim)；读取单个或者多个元素；根据值来查找或者移除元素   ##### */

    /**
     * 返回List列表的长度。 如果列表 key 不存在，则 key 被解释为一个空列表，返回 0 。
     * LLEN KEY_NAME
     * redis 127.0.0.1:6379> RPUSH list1 "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> RPUSH list1 "bar"
     * (integer) 2
     * redis 127.0.0.1:6379> LLEN list1
     * (integer) 2
     * @param key
     * @return 返回列表的长度
     */
    public Long llen(String key){
        return redisTemplate.boundListOps(key).size();
    }

    /**
     * 设置List　集合值覆盖操作,将覆盖List中指定位置的值
     * LSET KEY_NAME INDEX VALUE
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 2
     * redis 127.0.0.1:6379> RPUSH mylist "foo"
     * (integer) 3
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 4
     * redis 127.0.0.1:6379> LSET mylist 0 "bar"
     * OK
     * redis 127.0.0.1:6379> LRANGE mylist 0 -1
     *  "bar"
     *  "hello"
     *  "foo"
     *  "hello"
     * @param key
     * @param index  指定的索引值
     * @param value  数据值
     */
    public void lset(String key,Long index,Object value){
        redisTemplate.boundListOps(key).set(index,value);
    }

    /**
     * 将List中的第一条记录移出List
     * Lpop KEY_NAME
     * redis 127.0.0.1:6379> RPUSH list1 "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> RPUSH list1 "bar"
     * (integer) 2
     * redis 127.0.0.1:6379> LPOP list1
     * "foo"
     * @param key
     * @return 第一条移除的纪录
     */
    public Object lpop(String key){
        return redisTemplate.boundListOps(key).leftPop();
    }

    /**
     * 将List中的第一条记录移出List
     * @param key
     * @param timeout  秒
     * @return  第一条移除的纪录
     */
    public Object lpop(String key,Long timeout){
        return redisTemplate.boundListOps(key).leftPop(timeout,TimeUnit.SECONDS);
    }


    /**
     *  将List中的最后最后一条纪录移除list
     *  RPOP KEY_NAME
     *  redis> RPUSH mylist "one"
     * (integer) 1
     * redis> RPUSH mylist "two"
     * (integer) 2
     * redis> RPUSH mylist "three"
     * (integer) 3
     * redis> RPOP mylist
     * "three"
     * redis> LRANGE mylist 0 -1
     * 1) "one"
     * 2) "two"
     * @param key
     * @return 最后一条移除的纪录
     */
    public Object rpop(String key){
        return redisTemplate.boundListOps(key).rightPop();
    }

    /**
     * 将List中的最后最后一条纪录移除list
     * @param key
     * @param timeout  秒
     * @return  最后一条移除的纪录
     */
    public Object rpop(String key,Long timeout){
        return redisTemplate.boundListOps(key).rightPop(timeout,TimeUnit.SECONDS);
    }

    /**
     *  通过索引获取list中的元素
     *  LINDEX KEY_NAME INDEX_POSITION
     *  redis 127.0.0.1:6379> LPUSH mylist "World"
     * (integer) 1
     *
     * redis 127.0.0.1:6379> LPUSH mylist "Hello"
     * (integer) 2
     *
     * redis 127.0.0.1:6379> LINDEX mylist 0
     * "Hello"
     *
     * redis 127.0.0.1:6379> LINDEX mylist -1
     * "World"
     *
     * redis 127.0.0.1:6379> LINDEX mylist 3        # index不在 mylist 的区间范围内
     * (nil)
     * @param key
     * @param index  索引
     * @return 列表中下标为指定索引值的元素
     */
    public Object lindex(String key,Long index){
        return redisTemplate.boundListOps(key).index(index);
    }

    /**
     * 将一个或多个值插入到列表头部(左边)。 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作
     *  LPUSH KEY_NAME VALUE1.. VALUEN
     *  127.0.0.1:6379> LPUSH list1 "foo"
     * (integer) 1
     * 127.0.0.1:6379> LPUSH list1 "bar"
     * (integer) 2
     * 127.0.0.1:6379> LRANGE list1 0 -1
     * 1) "bar"
     * 2) "foo"
     * @param key
     * @param value
     * @return 执行 LPUSH 命令后，列表的长度。
     */
    public Long lpush(String key,Object value){
        return redisTemplate.boundListOps(key).leftPush(value);
    }

    /**
     * 将一个或多个值插入到列表头部(左边)。 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作
     *  LPUSH KEY_NAME VALUE1.. VALUEN
     *  127.0.0.1:6379> LPUSH list1 "foo"
     * (integer) 1
     * 127.0.0.1:6379> LPUSH list1 "bar"
     * (integer) 2
     * 127.0.0.1:6379> LRANGE list1 0 -1
     * 1) "bar"
     * 2) "foo"
     * @param key
     * @param objects
     * @return 执行 LPUSH 命令后，列表的长度。
     */
    public Long lpush(String key,Object... objects){
        return redisTemplate.boundListOps(key).leftPushAll(objects);
    }

    /**
     *  用于在列表的元素前或者后插入元素(左边)。当指定元素不存在于列表中时，不执行任何操作。
     *  LINSERT key BEFORE|AFTER pivot value
     *  redis> RPUSH mylist "Hello"
     * (integer) 1
     * redis> RPUSH mylist "World"
     * (integer) 2
     * redis> LINSERT mylist BEFORE "World" "There"
     * (integer) 3
     * redis> LRANGE mylist 0 -1
     * 1) "Hello"
     * 2) "There"
     * 3) "World"
     * @param key
     * @param pivot  目标位置元素
     * @param value  插入的元素值
     * @return 如果命令执行成功，返回插入操作完成之后，列表的长度
     */
    public Long lpush(String key,Object pivot,Object value ){
        return redisTemplate.boundListOps(key).leftPush(pivot,value);
    }



    /**
     *  将一个值插入到已存在的列表头部(左边)，列表不存在时操作无效。
     *   LPUSHX KEY_NAME VALUE1.. VALUEN
     *   127.0.0.1:6379> LPUSH list1 "foo"
     * (integer) 1
     * 127.0.0.1:6379> LPUSHX list1 "bar"
     * (integer) 2
     * 127.0.0.1:6379> LPUSHX list2 "bar"
     * (integer) 0
     * 127.0.0.1:6379> LRANGE list1 0 -1
     * 1) "bar"
     * 2) "foo"
     * @param key
     * @param value
     * @return LPUSHX 命令执行之后，列表的长度。
     */
    public Long Lpushx (String key,Object value){
        return redisTemplate.boundListOps(key).leftPushIfPresent(value);
    }

    /**
     * 将一个或多个值插入到列表的尾部(最右边)。
     * 如果列表不存在，一个空列表会被创建并执行 RPUSH 操作
     * RPUSH KEY_NAME VALUE1..VALUEN
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> RPUSH mylist "foo"
     * (integer) 2
     * redis 127.0.0.1:6379> RPUSH mylist "bar"
     * (integer) 3
     * redis 127.0.0.1:6379> LRANGE mylist 0 -1
     * 1) "hello"
     * 2) "foo"
     * 3) "bar"
     * @param key
     * @param value
     * @return 执行 RPUSH 操作后，列表的长度。
     */
    public Long rpush(String key,Object value){
        return redisTemplate.boundListOps(key).rightPush(value);
    }

    /**
     * 将一个或多个值插入到列表的尾部(最右边)。
     * 如果列表不存在，一个空列表会被创建并执行 RPUSH 操作
     * RPUSH KEY_NAME VALUE1..VALUEN
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> RPUSH mylist "foo"
     * (integer) 2
     * redis 127.0.0.1:6379> RPUSH mylist "bar"
     * (integer) 3
     * redis 127.0.0.1:6379> LRANGE mylist 0 -1
     * 1) "hello"
     * 2) "foo"
     * 3) "bar"
     * @param key
     * @param values
     * @return 执行 RPUSH 操作后，列表的长度。
     */
    public Long rpush(String key,Object... values){
        return redisTemplate.boundListOps(key).rightPush(values);
    }


    /**
     *  用于在列表的元素前或者后插入元素(右边)。当指定元素不存在于列表中时，不执行任何操作。
     *  LINSERT key BEFORE|AFTER pivot value
     *  redis> RPUSH mylist "Hello"
     * (integer) 1
     * redis> RPUSH mylist "World"
     * (integer) 2
     * redis> LINSERT mylist BEFORE "World" "There"
     * (integer) 3
     * redis> LRANGE mylist 0 -1
     * 1) "Hello"
     * 2) "There"
     * 3) "World"
     * @param key
     * @param pivot  目标位置元素
     * @param value  插入的元素值
     * @return 如果命令执行成功，返回插入操作完成之后，列表的长度
     */
    public Long rpush(String key,Object pivot,Object value ){
        return redisTemplate.boundListOps(key).rightPush(pivot,value);
    }

    /**
     *  将一个值插入到已存在的列表尾部(最右边)。如果列表不存在，操作无效。
     *  RPUSHX KEY_NAME VALUE1..VALUEN
     *   redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> RPUSH mylist "foo"
     * (integer) 2
     * redis 127.0.0.1:6379> RPUSHX mylist2 "bar"
     * (integer) 0
     * redis 127.0.0.1:6379> LRANGE mylist 0 -1
     * 1) "hello"
     * 2) "foo"
     * @param key
     * @param value
     * @return  执行 Rpushx 操作后，列表的长度。
     */
    public Long rpushx(String key,Object value){
        return redisTemplate.boundListOps(key).leftPushIfPresent(value);
    }

    /**
     * 获取指定范围的记录，可以做为分页使用
     * 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * LRANGE KEY_NAME START END
     * @param key
     * @param start  开始索引
     * @param end    结束索引
     * @return 返回列表中指定区间内的元素
     */
    public List<Object> lrange(String key,Long start,Long end){
        return redisTemplate.boundListOps(key).range(start,end);
    }

    /**
     * 获取 key 的所有元素
     * @param key
     * @return
     */
    public List<Object> lrange(String key){
       return lrange(key,0L,llen(key));
    }

    /**
     * 让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * LTRIM KEY_NAME START STOP
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 2
     * redis 127.0.0.1:6379> RPUSH mylist "foo"
     * (integer) 3
     * redis 127.0.0.1:6379> RPUSH mylist "bar"
     * (integer) 4
     * redis 127.0.0.1:6379> LTRIM mylist 1 -1
     * OK
     * redis 127.0.0.1:6379> LRANGE mylist 0 -1
     * 1) "hello"
     * 2) "foo"
     * 3) "bar"
     * @param key
     * @param start  开始索引
     * @param end 结束索引
     */
    public void  ltrim(String key,Long start,Long end){
         redisTemplate.boundListOps(key).trim(start,end);
    }


    /**
     *  根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素
     *
     *  count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     *
     *  LREM KEY_NAME COUNT VALUE
     *
     *  redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 1
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 2
     * redis 127.0.0.1:6379> RPUSH mylist "foo"
     * (integer) 3
     * redis 127.0.0.1:6379> RPUSH mylist "hello"
     * (integer) 4
     * redis 127.0.0.1:6379> LREM mylist -2 "hello"
     * (integer) 2
     *
     * @param key
     * @param count
     * @param value  值
     * @return 被移除元素的数量。 列表不存在时返回 0
     */
    public Long lrem(String key,Long count ,Object value){
        return redisTemplate.boundListOps(key).remove(count,value);
    }

    /** #######################################   List 类型操作结束    ################################################################# */



    /** #######################################   Hash 类型操作开始    ################################################################# */

    /**
     *  删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略
     *
     *  HDEL KEY_NAME FIELD1.. FIELDN
     *
     *  redis 127.0.0.1:6379> HSET myhash field1 "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> HDEL myhash field1
     * (integer) 1
     * redis 127.0.0.1:6379> HDEL myhash field2
     * (integer) 0
     *
     * @param key
     * @param fields  要删除的字段
     * @return 被成功删除字段的数量
     */
    public Long hdel(String key,String... fields){
        return redisTemplate.boundHashOps(key).delete(fields);
    }

    /**
     * 查看哈希表的指定字段是否存在
     * HEXISTS KEY_NAME FIELD_NAME
     *redis 127.0.0.1:6379> HSET myhash field1 "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> HEXISTS myhash field1
     * (integer) 1
     * redis 127.0.0.1:6379> HEXISTS myhash field2
     * (integer) 0
     *
     * @param key
     * @param field
     * @return  存在返回true  不存在返回false
     */
    public  Boolean hexists(String key,String field){
        return redisTemplate.boundHashOps(key).hasKey(field);
    }

    /**
     *  返回哈希表中指定字段的值
     *
     *  HGET KEY_NAME FIELD_NAME
     *
     *  redis> HSET site redis redis.com
     * (integer) 1
     *
     * redis> HGET site redis
     * "redis.com"
     *
     * @param key
     * @param field 指定的字段
     * @return  返回给定字段的值
     */
    public  Object hget(String key,String field){
        return redisTemplate.boundHashOps(key).get(field);
    }

    /**
     * 返回哈希表中，所有的字段和值
     * HGETALL KEY_NAME
     * redis> HGETALL myhash
     * 1) "field1"
     * 2) "Hello"
     * 3) "field2"
     * 4) "World"
     * redis>
     * @param key
     * @return 返回哈希表的字段及字段值。 若 key 不存在，返回空列表
     */
    public Map<String,Object> hgetAll(String key){
        return redisTemplate.boundHashOps(key).entries();
    }

    /**
     * 为哈希表中的字段赋值
     * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，旧值将被覆盖。
     *
     *  HSET KEY_NAME FIELD VALUE
     *
     * redis 127.0.0.1:6379> HSET myhash field1 "foo"
     * OK
     * redis 127.0.0.1:6379> HGET myhash field1
     * "foo"
     *
     * redis 127.0.0.1:6379> HSET website google "www.g.cn"       # 设置一个新域
     * (integer) 1
     *
     * redis 127.0.0.1:6379>HSET website google "www.google.com" # 覆盖一个旧域
     * (integer) 0
     *
     * @param key
     * @param field
     * @param value
     */
    public  void  hset(String key, String field, String value) {
         redisTemplate.boundHashOps(key).put(field,value);
    }

    /**
     * 为哈希表中的字段赋值
     * @param key
     * @param map
     */
    public void hmset(String key,Map<String,Object> map){
        redisTemplate.boundHashOps(key).putAll(map);
    }

    /**
     * 为哈希表中的字段赋值 并设置获取时间
     * @param key
     * @param field  赋值字段
     * @param value  值
     * @param time   过期时间（秒）
     */
    public  void  hset(String key, String field, String value,Long time){
        redisTemplate.boundHashOps(key).put(field,value);
        redisTemplate.boundHashOps(key).expire(time,TimeUnit.SECONDS);
    }

    /**
     * 为哈希表中不存在的的字段赋值
     * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，操作无效。
     *
     * HSETNX KEY_NAME FIELD VALUE
     * redis 127.0.0.1:6379> HSETNX nosql key-value-store redis
     * (integer) 1
     *
     * redis 127.0.0.1:6379> HSETNX nosql key-value-store redis       # 操作无效， key-value-store 已存在
     * (integer) 0
     *
     * @param key
     * @param field
     * @param value
     * @return  设置成功返回true  设置失败返回false
     */
    public Boolean hsetnx(String key,String field,Object value){
        return redisTemplate.boundHashOps(key).putIfAbsent(field,value);
    }

    /**
     * 返回哈希表所有域(field)的值
     *  HVALS KEY_NAME FIELD VALUE
     * redis 127.0.0.1:6379> HSET myhash field1 "foo"
     * (integer) 1
     * redis 127.0.0.1:6379> HSET myhash field2 "bar"
     * (integer) 1
     * redis 127.0.0.1:6379> HVALS myhash
     * 1) "foo"
     * 2) "bar"
     * @param key
     * @return 一个包含哈希表中所有域(field)值的列表。 当 key 不存在时，返回一个空表
     */
    public List<Object> hvals(String key){
        return redisTemplate.boundHashOps(key).values();
    }


    /**
     *  用于为哈希表中的字段值加上指定增量值
     *  增量也可以为负数，相当于对指定字段进行减法操作
     *
     *  HINCRBY KEY_NAME FIELD_NAME INCR_BY_NUMBER
     *  redis> HSET myhash field 5
     * (integer) 1
     * redis> HINCRBY myhash field 1
     * (integer) 6
     * redis> HINCRBY myhash field -1
     * (integer) 5
     * redis> HINCRBY myhash field -10
     * (integer) -5
     * redis>
     *
     * @param key
     * @param field   需要计算的字段
     * @param number  增量值
     * @return  返回计算后哈希表中字段的值
     */
    public Long hincrby(String key,String field,Long number){
        return redisTemplate.boundHashOps(key).increment(field,number);
    }

    /**
     *  用于为哈希表中的字段值加上指定增量值
     *  增量也可以为负数，相当于对指定字段进行减法操作
     *
     *  HINCRBY KEY_NAME FIELD_NAME INCR_BY_NUMBER
     *  redis> HSET myhash field 5
     * (integer) 1
     * redis> HINCRBY myhash field 1
     * (integer) 6
     * redis> HINCRBY myhash field -1
     * (integer) 5
     * redis> HINCRBY myhash field -10
     * (integer) -5
     * redis>
     *
     * @param key
     * @param field   需要计算的字段
     * @param number  增量值
     * @return  返回计算后哈希表中字段的值
     */
    public Double hincrby(String key,String field,Double number){
        return redisTemplate.boundHashOps(key).increment(field,number);
    }

    /**
     * 用于获取哈希表中的所有域（field）
     *   HKEYS key
     *   redis 127.0.0.1:6379> HKEYS myhash
     * 1) "field1"
     * 2) "field2"
     * @param key
     * @return  返回哈希表中所有域（field）列表
     */
    public Set<Object> hkeys(String key){
        return redisTemplate.boundHashOps(key).keys();
    }

    /**
     * 获取hash中存储的个数，类似Map中size方法
     * @param key
     * @return
     */
    public Long hlen(String key){
        return redisTemplate.boundHashOps(key).size();
    }



    /** #######################################   Hash 类型操作结束    ################################################################# */

}
