package com.lmt.code.test;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class OracleJdbcTest {

    public static void main1(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//10.1.12.141:1523/pdev12uf","sjbusi", "ccic1234");
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select table_name from user_tables");
        while(rs.next()) {
            String tableName = rs.getString("table_name");
            System.out.println(tableName);
        }
        ResultSet columnRs = conn.getMetaData().getColumns(null, "SJBUSI", "%", "%");
        while (columnRs.next()) {
            String tableName = columnRs.getString("table_name");
            String columnName = columnRs.getString("column_name");
            System.out.println(tableName + " " + columnName);
        }
        columnRs.close();
//        rs.close();
//        DatabaseMetaData metaData = conn.getMetaData();
//        rs = metaData.getTables(null, "SJBUSI", null, new String[]{"TABLE", "view"});
//        ResultSetMetaData resultSetMetaData = rs.getMetaData();
//        int columnCount = resultSetMetaData.getColumnCount();
//        System.out.println(columnCount);
//        while(rs.next()) {
//            for(int i=1; i<=columnCount; i++) {
//                String columnLabel = resultSetMetaData.getColumnLabel(i);
//                String colunName = resultSetMetaData.getColumnName(i);
//                int columnType = resultSetMetaData.getColumnType(i);
//                String columnTypeName = resultSetMetaData.getColumnTypeName(i);
//                Object obj = rs.getObject(i);
//                System.out.println(columnLabel + " " + colunName + " " + columnType + " " + columnTypeName + " " + obj);
//            }
//        }


        rs.close();
        stat.close();
        conn.close();
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db01:11521/XEPDB1","ducx", "0809");
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select table_name from user_tables");
        while(rs.next()) {
            String tableName = rs.getString("table_name");
            System.out.println(tableName);
        }
        ResultSet columnRs = conn.getMetaData().getColumns(null, "DUCX", "%", "%");
        while (columnRs.next()) {
            String tableName = columnRs.getString("table_name");
            String columnName = columnRs.getString("column_name");
            System.out.println(tableName + " " + columnName);
        }
        columnRs.close();
        rs.close();
        DatabaseMetaData metaData = conn.getMetaData();
        rs = metaData.getTables(null, "DUCX", null, new String[]{"TABLE", "view"});
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println(columnCount);
        while(rs.next()) {
            for(int i=1; i<=columnCount; i++) {
                String columnLabel = resultSetMetaData.getColumnLabel(i);
                String colunName = resultSetMetaData.getColumnName(i);
                int columnType = resultSetMetaData.getColumnType(i);
                String columnTypeName = resultSetMetaData.getColumnTypeName(i);
                Object obj = rs.getObject(i);
                System.out.println(columnLabel + " " + colunName + " " + columnType + " " + columnTypeName + " " + obj);
            }
        }

        rs = stat.executeQuery("select * from t_user");
        ResultSetMetaData msmd = rs.getMetaData();
        columnCount = msmd.getColumnCount();
        for(int i=1; i<=columnCount; i++) {
            String columnLabel = msmd.getColumnLabel(i);
            String colunName = msmd.getColumnName(i);
            int columnType = msmd.getColumnType(i);
            String columnTypeName = msmd.getColumnTypeName(i);
            int precision = msmd.getPrecision(i);
            int scale = msmd.getScale(i);
            System.out.println("类型信息:" + columnLabel + " " + colunName + " " + columnType + " " + columnTypeName + " " + precision + " " + scale);
        }
        while(rs.next()) {
            for(int i=1; i<=columnCount; i++) {
                Object obj = rs.getObject(i);
                System.out.println(obj);
            }
        }


        rs.close();
        stat.close();
        conn.close();
    }
}
