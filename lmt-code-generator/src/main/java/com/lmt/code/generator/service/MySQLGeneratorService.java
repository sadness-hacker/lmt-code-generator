/**
 * Copyright (c) 2018 XiaoXuanKeJi All Rights Reserved.
 */
package com.lmt.code.generator.service;

import com.lmt.code.bean.ColumnBean;
import com.lmt.code.bean.TableBean;
import com.lmt.code.exception.BasicException;
import com.lmt.code.type.MySQLTypeEnum;

import java.sql.ResultSet;

/**
 * 
 * @description
 * @author bazhandao
 * @date 2018年10月22日 上午11:43:23
 * @since JDK 1.8
 * 
 */
public class MySQLGeneratorService extends AbstractGeneratorService {

    private static final String dbType = "mysql";

    /**
     * 驱动名称
     */
    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
    /**
     * 获取数据库类型
     *
     * @return
     * @author bazhandap
     * @date 2018-11-02
     */
    @Override
    public String getDbType() {
        return dbType;
    }

    /**
     * 获取数据库驱动名
     *
     * @return
     * @author bazhandap
     * @date 2018-11-02
     */
    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * ResultSet创建TableBean(获取表名，表注释字段)
     *
     * @param rs
     * @return
     * @author bazhandap
     * @date 2018-11-02
     */
    @Override
    public TableBean buildTableBean(ResultSet rs) throws Exception {
        TableBean tb = new TableBean();
        String tableName = rs.getString("table_name");
        String comments = rs.getString("table_comment");
        tb.setTableName(tableName);
        tb.setTableComment(comments);
        return tb;
    }

    /**
     * ResultSet创建ColumnBean(获取字段名，表名，字段注释，字段类型，字段长度等)
     *
     * @param rs
     * @return
     * @author bazhandap
     * @date 2018-11-02
     */
    @Override
    public ColumnBean buildColumnBean(ResultSet rs) throws Exception {
        ColumnBean cb = new ColumnBean();
        String tableName = rs.getString("table_name");
        String columnName = rs.getString("column_name");
        String dataType = rs.getString("data_type");
        if(dataType.indexOf("(") > 0) {
            dataType = dataType.substring(0, dataType.indexOf("("));
        }
        int dataLength = rs.getInt("data_length");
        String comments = rs.getString("column_comment");
        cb.setTableName(tableName);
        cb.setColumnComment(comments);
        cb.setColumnName(columnName);
        cb.setJdbcType(dataType);
        cb.setDataLength(dataLength);
        MySQLTypeEnum type = MySQLTypeEnum.getByJdbcType(dataType);
        if(type == null) {
            throw new BasicException("2300", "MySQL类型未知,jdbcType=" + dataType);
        }
        cb.setJavaType(type.getJavaType());
        cb.setImportClass(type.getImportClass());
        return cb;
    }

}
