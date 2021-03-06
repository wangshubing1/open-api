package cn.com.belle.bdc.openapi.util;

import java.util.UUID;
/**
 * 
 * @descript：生成UUID
 */
public class UUIDUtil {

    /**
     * 随机获取UUID字符串(无中划线)
     * 
     * @return UUID字符串
     */
    public synchronized static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
               + uuid.substring(24);
    }
}
