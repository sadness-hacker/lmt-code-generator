package com.lmt.code.util;

import com.lmt.code.exception.BasicException;

/**
 * @description 字符串工具类
 *
 * @author bazhandao
 * @date 2018/11/2 16:39
 * @since JDK1.8
 */
public class StringUtil {

    /**
     * 首字母转小写
     * @author bazhandao
     * @date 2018/11/2 16:39
     * @param s
     * @return
     */
    public static String lowerFirstChar(String s) {
        if(s == null || "".equals(s.trim())) {
            return s;
        }
        s = s.trim();
        String first = String.valueOf(s.charAt(0));
        return s.replaceFirst(first, first.toLowerCase());
    }

    /**
     * 首字母转大写
     * @author bazhandao
     * @date 2018/11/2 16:39
     * @param s
     * @return
     */
    public static String upperFirstChar(String s) {
        if(s == null || "".equals(s.trim())) {
            return s;
        }
        s = s.trim();
        String first = String.valueOf(s.charAt(0));
        return s.replaceFirst(first, first.toUpperCase());
    }

    /**
     * 表名转类名
     * @author bazhandao
     * @date 2018/11/2 16:39
     * @param tableName
     * @return
     */
    public static String toClassName(String tableName) {
        if(tableName == null || "".equals(tableName.trim())) {
            throw new BasicException("2100", "非法的表名");
        }
        tableName = tableName.trim().toLowerCase();
        String [] arr = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            if(s.isEmpty()) {
                continue;
            }
            s = upperFirstChar(s);
            sb.append(s);
        }
        return sb.toString();
    }

}
