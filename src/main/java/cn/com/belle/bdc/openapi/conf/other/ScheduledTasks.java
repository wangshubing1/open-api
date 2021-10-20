package cn.com.belle.bdc.openapi.conf.other;

import cn.com.belle.bdc.openapi.service.CacheService;
import cn.com.belle.bdc.openapi.util.JedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时每天五点检查数据是否更新完，更新完则清理一次redis缓存
 */
@Component
public class ScheduledTasks {
    private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Resource
    private CacheService cacheService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 5 * * ?")//"0 0 5 * * ?"每天5点00分00秒触发任务
    public void reportCurrentTime() {
        try{
            //获取机器名和服务端口号
            InetAddress netAddress = InetAddress.getLocalHost();
            String hostName = netAddress.getHostName();
            logger.info("主机名=" + hostName);
            //bjds-bdc-open-api-prd-10-240-118-71-vm.belle.lan节点执行定时任务
            if ("bjds-bdc-open-api-prd-10-240-118-71-vm.belle.lan".equals(hostName)) {
                //如果update_time和end_time日期相等，并且end_time大于update_time，则符合清理缓存
                while (true){
                    //检查大算底层是否已经更新完
                    String impala_updated_sql = "select max(update_time) as update_time from bi_shoes_dsp.app_day_org_kpi";
                    Map<String, Object> paramsMap = new HashMap<>();
                    paramsMap.put("sql", impala_updated_sql);
                    List<Map<String, Object>> datas = cacheService.queryUpdateTime(paramsMap);
                    String update_time = (String) datas.get(0).get("update_time");
                    //检查数据中台是否已经更新完
                    String mysql_updated_sql = "select max(end_time) as end_time from cdh_cl_ctl.oth_etl_session_logs where target_table_name ='app_day_org_kpi'";
                    Map<String, Object> paramsMap2 = new HashMap<>();
                    paramsMap2.put("sql", mysql_updated_sql);
                    List<Map<String, Object>> datas2 = cacheService.queryEndTime(paramsMap2);
                    String end_time = datas2.get(0).get("end_time").toString();
                    logger.info("if clear cache --> update_time:"+update_time+",end_time:"+end_time);
                    if(update_time.substring(0,10).equals(end_time.substring(0,10))
                            && 0 > update_time.compareTo(end_time)){
                        logger.info("begin clear cache:"+end_time);
                        Jedis jedis = null;
                        try{
                            jedis=JedisPoolUtil.getJedis();
                            Set<String> set = jedis.keys("bdc-api-dgb-get*");
                            for (String e : set) {
                                System.out.println("delete this key -> " + e + ", result -> " + jedis.del(e));
                            }
                            logger.info("finish clear cache");
                            break;
                        }catch (Exception e){
                            e.printStackTrace();
                            break;
                        } finally {
                            if(jedis!=null){
                                jedis.close();
                            }
                        }
                    }else{
                        try {
                            Thread.sleep(600000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("定时清理接口缓存异常。"+e.getStackTrace());
        }
    }

    public static Properties getJedisProperties() {

        Properties config = new Properties();
        InputStream is = null;
        try {
            is = JedisPoolUtil.class.getClassLoader().getResourceAsStream("scheduleclearcache.properties");
            config.load(is);
        } catch (IOException e) {
            logger.error("scheduleclearcache配置读取失败", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("InputStream关闭异常", e);
                }
            }
        }
        return config;
    }

//    public static void main(String[] args){
//        Properties prop = getJedisProperties();
//        Set<Map.Entry<Object,Object>> entrySet = prop.entrySet();
//        Iterator it=entrySet.iterator();
//        Object key;
//        Object value;
//        while(it.hasNext()){
//            Map.Entry entry = (Map.Entry)it.next();
//            key=entry.getKey();
//            value=entry.getValue();
//            System.out.println(key+"="+value);
//        }
//    }

}
