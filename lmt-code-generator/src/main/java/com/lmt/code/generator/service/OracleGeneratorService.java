/**
 * Copyright (c) 2018 XiaoXuanKeJi All Rights Reserved.
 */
package com.lmt.code.generator.service;

import com.lmt.code.bean.ColumnBean;
import com.lmt.code.bean.TableBean;
import com.lmt.code.exception.BasicException;
import com.lmt.code.type.OracleTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;

/**
 * 
 * @description
 * @author bazhandao
 * @date 2018年10月22日 上午11:44:48
 * @since JDK 1.8
 * 
 */
@Slf4j
public class OracleGeneratorService extends AbstractGeneratorService {


    private static final String dbType = "oracle";

    /**
     * 驱动名称
     */
    private static final String driverClassName = "oracle.jdbc.driver.OracleDriver";


    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public String getDbType() {
        return dbType;
    }

    @Override
    public TableBean buildTableBean(ResultSet rs) throws Exception {
        TableBean tb = new TableBean();
        String tableName = rs.getString("table_name");
        String comments = rs.getString("comments");
        tb.setTableName(tableName);
        tb.setTableComment(comments);
        return tb;
    }

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
        String comments = rs.getString("comments");
        cb.setTableName(tableName);
        cb.setColumnComment(comments);
        cb.setColumnName(columnName);
        cb.setDataLength(dataLength);
        OracleTypeEnum type = OracleTypeEnum.getByJdbcType(dataType);
        if(type == null) {
            throw new BasicException("2200", "Oracle类型未知,jdbcType=" + dataType);
        }
        cb.setJdbcType(type.getJdbcType());
        cb.setJavaType(type.getJavaType());
        cb.setImportClass(type.getImportClass());
        //数值类型单独处理
        if(type.getType() == 2) {
            int scale = rs.getInt("data_scale");
            int precision = rs.getInt("data_precision");
            //整数类型
            if(scale == 0) {
                //1~4	Short
                //5~9	Integer
                //10~18	Long
                //18+	BigDecimal
                cb.setImportClass(null);
                if(precision <= 4) {
                    cb.setJavaType("Short");
                } else if(precision > 4 && precision <= 9) {
                    cb.setJavaType("Integer");
                } else if(precision > 9 && precision <= 18) {
                    cb.setJavaType("Long");
                } else {
                    cb.setJavaType("BigInteger");
                    cb.setImportClass("java.math.BigInteger");
                }
            }
        }

        return cb;
    }
}
