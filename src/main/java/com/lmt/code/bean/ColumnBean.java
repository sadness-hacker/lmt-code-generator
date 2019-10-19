package com.lmt.code.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description 表字段信息
 *
 * @author bazhandao
 * @date 2018/11/1 13:19
 * @since JDK1.8
 */
@Getter
@Setter
public class ColumnBean implements Serializable {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 数据长度
     */
    private long dataLength;

    /**
     * 字段名
     */
    private String columnName;
    /**
     * jdbc类型
     */
    private String jdbcType;
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * java字段名
     */
    private String javaName;
    /**
     * 方法java名（javaName首字母大写）
     */
    private String javaNameFirstUpper;
    /**
     * java类型
     */
    private String javaType;
    /**
     * 需要import的class名
     */
    private String importClass;

}
