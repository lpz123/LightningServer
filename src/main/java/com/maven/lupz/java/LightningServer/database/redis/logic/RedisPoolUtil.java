package com.maven.lupz.java.LightningServer.database.redis.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.maven.lupz.java.LightningServer.database.redis.proto.redis_proto;
import com.maven.lupz.java.LightningServer.database.redis.proto.redis_proto.RedisGroup;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 单片连接无需切片设置
 * @author lupz
 */
public class RedisPoolUtil {
	private static JedisPool jedisPool = null;
    // Redis服务器IP
    private static String ADDRESS = "192.168.91.4";
    // Redis的端口号
    private static int PORT = 6379;
    // 访问密码
    private static String AUTH = "123456";
    //锁
    private static Object lock=new Object();

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            config.setBlockWhenExhausted(true);
            // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            // 是否启用pool的jmx管理功能, 默认true
            config.setJmxEnabled(true);
            // 最大空闲连接数, 默认8个 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(8);
            // 最大连接数, 默认8个
            config.setMaxTotal(200);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(1000 * 100);
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            jedisPool = new JedisPool(config, ADDRESS, PORT, 3000, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     */
    private static Jedis getJedis() {
    	synchronized(lock){
    		Jedis jedis=null;
            try {
                if (jedisPool != null) {
                    jedis = jedisPool.getResource();
                    return jedis;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    	}
    }

    /**
     * 释放jedis资源
     */
    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
    
    /**
     * 获取唯一id
     */
    public static String getOnlyId(){
    	Jedis jedis=getJedis();
    	try{
    		return jedis.incr("onlyId")+"";
    	}catch(Exception ex){
    		jedis.set("onlyId", "10000");
    		return jedis.incr("onlyId")+"";
    	}finally {
			if(jedis!=null){
				close(jedis);
			}
		}
    }
    
    /**
     * 添加
     */
    public static void addObj(String dbName,String roleId,byte[] byteArray){
    	Jedis jedis=getJedis();
    	try{
    		Map<byte[],byte[]> newMap=new HashMap<>();
    		newMap.put(roleId.getBytes(), byteArray);
    		jedis.hmset(dbName.getBytes(), newMap);
//    		jedis.set(roleId.getBytes(), byteArray);
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(jedis!=null){
				close(jedis);
			}
		}
    }
    
    /**
     * 批量添加
     */
    public static void addSomeObj(String dbName,Map<String,byte[]> map){
    	Jedis jedis=getJedis();
    	try{
    		Map<byte[],byte[]> newMap=new HashMap<>();
    		for(String str:map.keySet()){
    			newMap.put(str.getBytes(), map.get(str));
    		}
    		jedis.hmset(dbName.getBytes(), newMap);
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(jedis!=null){
				close(jedis);
			}
		}
    }
    
    /**
     * 清空库
     */
    public static void flushAll(){
    	Jedis jedis=getJedis();
    	jedis.flushAll();
    }
    
    /**
     * 通过库name和角色id获取角色数据
     */
    public static byte[] getObj(String dbName,String roleId){
    	Jedis jedis=getJedis();
    	try{
    		List<byte[]> roleByte=jedis.hmget(dbName.getBytes(), roleId.getBytes());
    		return roleByte.get(0);
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(jedis!=null){
				close(jedis);
			}
		}
    	return null;
    }
    
//    public static void addSomeObj(){
//    	Jedis jedis=getJedis();
//    }
//    
//    public static void getObj(){
//    	Jedis jedis=getJedis();
//    }
//    
//    public static void getSomeObj(){
//    	Jedis jedis=getJedis();
//    }

    /**
     * 字符串测试
     * 
     * @param jedis
     */
    public static void testString(Jedis jedis) {
        jedis.set("name", "xxxx");// 向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));// 执行结果：xinxin

        jedis.append("name", " is my lover"); // 拼接
        System.out.println(jedis.get("name"));

        jedis.del("name"); // 删除某个键
        System.out.println(jedis.get("name"));
        // 设置多个键值对
        jedis.mset("name", "某某某", "age", "24", "qq", "476777XXX");
        jedis.incr("age"); // 进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
    }

    /**
     * map 用法
     * 
     * @param jedis
     */
    public static void testMap(Jedis jedis) {
        // -----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);
        // 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        // 删除map中的某个键值
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age")); // 因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); // 返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));// 是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));// 返回map对象中的所有key
        System.out.println(jedis.hvals("user"));// 返回map对象中的所有value

        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
    }

    /**
     * jedis操作List
     */
    public static void testList(Jedis jedis) {
        // 开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        // 先向key java framework中存放三条数据
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        // 再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework", 0, -1));

        jedis.del("java framework");
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");
        jedis.rpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));
    }

    /** 
     * jedis操作Set 
     */  
    public static void testSet(Jedis jedis){  
        //添加  
        jedis.sadd("user","liuling");  
        jedis.sadd("user","xinxin");  
        jedis.sadd("user","ling");  
        jedis.sadd("user","zhangxinxin");
        jedis.sadd("user","who");  
        //移除noname  
        jedis.srem("user","who");  
        System.out.println(jedis.smembers("user"));//获取所有加入的value  
        System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素  
        System.out.println(jedis.srandmember("user"));  
        System.out.println(jedis.scard("user"));//返回集合的元素个数  
    }  
}
