package cn.com.belle.bdc.openapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 *
 */
public class JedisPoolUtil {

    private static Logger logger = LoggerFactory.getLogger(JedisPoolUtil.class);

    private static JedisSentinelPool pool = null;

    public static Properties getJedisProperties() {

        Properties config = new Properties();
        InputStream is = null;
        try {
            is = JedisPoolUtil.class.getClassLoader().getResourceAsStream("application.properties");
            config.load(is);
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        return config;
    }

    /**
     * 创建连接池
     *
     */
    private static void createJedisPool() {
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        Properties prop = getJedisProperties();
        // 设置最大连接数
        config.setMaxTotal(Integer.valueOf(prop.getProperty("spring.redis.pool.max-active")));
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(Integer.valueOf(prop.getProperty("spring.redis.pool.max-wait")));
        // 设置空间连接
        config.setMaxIdle(Integer.valueOf(prop.getProperty("spring.redis.pool.max-idle")));
        // jedis实例是否可用
        boolean borrow = prop.getProperty("TEST_ON_BORROW") == "false" ? false : true;
        config.setTestOnBorrow(borrow);
        // 创建连接池
//      pool = new JedisPool(config, prop.getProperty("ADDR"), StringUtil.nullToInteger(prop.getProperty("PORT")), StringUtil.nullToInteger(prop.getProperty("TIMEOUT")));// 线程数量限制，IP地址，端口，超时时间
        //获取redis密码
        String password = prop.getProperty("spring.redis.password");
        String nodes = prop.getProperty("spring.redis.sentinel.nodes");
        String[] array = nodes.split(",");
        String masterName = prop.getProperty("spring.redis.sentinel.master");
        Set<String> sentinels = new HashSet<String>();
        sentinels.add(array[0]);
        sentinels.add(array[1]);
        sentinels.add(array[2]);
        pool = new JedisSentinelPool(masterName, sentinels, config, 10000, password);
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     *
     * @return
     */
    public static Jedis getJedis() {
        if (pool == null)
            poolInit();
        return pool.getResource();
    }

    /**
     * 释放一个连接
     *
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        pool.close();
    }

    /**
     * 销毁一个连接
     *
     * @param jedis
     */
    public static void returnBrokenRes(Jedis jedis) {
        pool.destroy();
    }


//    public static void main(String[] args){
//
//        Jedis jedis = null;
//        try{
//            jedis=getJedis();
////            jedis.set("sentinel","hello sentinel");
////            logger.info(jedis.get("sentinel"));
//
//            Thread.sleep(5000);
//            Set<String> set = jedis.keys("bdc-api-dgb-get*");//bdc-api-dgb-getRegionBrandSalamtsame
//            for (String e : set) {
//                System.out.println("delete this key -> " + e + ", result -> "
//                        + jedis.del(e));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            if(jedis!=null){
//                jedis.close();
//            }
//        }
//    }

}