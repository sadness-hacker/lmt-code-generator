package com.lmt.code.util;

import com.lmt.code.exception.BasicException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @description 文件工具类
 *
 * @author bazhandao
 * @date 2018/11/2 16:33
 * @since JDK1.8
 */
public class FileUtil {

    /**
     * 清空目录
     * @author bazhandao
     * @date 2018-11-02
     * @param path
     */
    public static void clear(String path) {
        File file = new File(path);
        if(file.exists()) {
            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e) {
                throw new BasicException(e);
            }
        }
        file.mkdirs();
    }

    /**
     * 创建目录
     * @author bazhandao
     * @date 2018-11-02
     * @param path
     */
    public static void mkdirs(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     *
     * 根据workspace目录、项目名、类全名，生成java文件路径
     * @author bazhandao
     * @date 2018-11-04
     * @param workspace
     * @param projectName
     * @param classFullName
     * @return
     */
    public static String toFilePath(String workspace, String projectName, String classFullName) {
        String path = workspace + "/" + projectName + "/" + classFullName;
        File file = new File(path);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return path;
    }

}
