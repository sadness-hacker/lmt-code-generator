package com.lmt.code.util;

import com.lmt.code.exception.BasicException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * @description Jdbc工具类
 *
 * @author bazhandao
 * @date 2018/11/2 12:07
 * @since JDK1.8
 */
@Slf4j
public class JdbcUtil {

    /**
     * 获取jdbc链接
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection(String driverClassName, String url, String username, String password) {
        try {
            Class.forName(driverClassName);
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            log.error("获取jdbc链接异常url={},username={}", url, username, e);
            throw new BasicException("8000", "获取jdbc链接异常");
        }
    }

    /**
     * 关闭jdbc链接
     * @param conn
     * @throws SQLException
     */
    public static void closeConnection(Connection conn) {
        if (conn == null) return;
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            log.error("关闭jdbc链接异常", e);
            throw new BasicException("8001", "关闭jdbc链接异常");
        }
    }

    /**
     * 关闭jdbc Statement
     * @param stat
     */
    public static void closeStatement(Statement stat) {
        if(stat == null) return;
        try {
            if(!stat.isClosed()) {
                stat.close();
            }
        } catch (Exception e) {
            log.error("关闭jdbc Statement异常", e);
            throw new BasicException("8002", "关闭jdbc Statement异常");
        }
    }

    /**
     * 关闭jdbc ResultSet
     * @param rs
     */
    public static void closeResultSet(ResultSet rs) {
        if(rs == null) return;
        try {
            if(!rs.isClosed()) {
                rs.close();
            }
        } catch (Exception e) {
            log.error("关闭jdbc ResultSet异常", e);
            throw new BasicException("8003", "关闭jdbc ResultSet异常");
        }
    }

}
