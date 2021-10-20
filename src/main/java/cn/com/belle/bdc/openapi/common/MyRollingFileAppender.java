package cn.com.belle.bdc.openapi.common;

import ch.qos.logback.core.rolling.RollingFileAppender;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lin.rg
 * @DateTime: 2019-7-10 11:35
 * @Description:
 */
public class MyRollingFileAppender extends RollingFileAppender {

    /**
     * 由于log4j 2.9 以下版本，生成的log日志文件读写权限为rw-r-----，
     * 导致其他用户（如：be_dev用户无法查看app用户）无法查看日志，需设置日志文件读写权限为rw-r--r--,
     * 让所有用户都有日志文件的读权限
     *
     * @param file
     *
     */
    @Override
    public void setFile(String file) {
        super.setFile(file);
        File f = new File(fileName);
        Set<PosixFilePermission> set = new HashSet<PosixFilePermission>();
        set.add(PosixFilePermission.OWNER_READ);
        set.add(PosixFilePermission.OWNER_WRITE);
        set.add(PosixFilePermission.GROUP_READ);
        set.add(PosixFilePermission.OTHERS_READ);
        if(f.exists()){
            try {
                Files.setPosixFilePermissions(f.toPath(), set);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
