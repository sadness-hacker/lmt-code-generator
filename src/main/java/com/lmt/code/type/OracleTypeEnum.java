package com.lmt.code.type;

import lombok.Getter;

/**
 *
 * @description Oracle数据库类型枚举类
 * @author bazhandao
 * @date 2018-11-04
 * @since JDK1.8
 *
 */
@Getter
public enum OracleTypeEnum {
    BIT(-7, "BIT", "Boolean", null),
    BOOLEAN(16, "BOOLEAN", "Boolean", null),
    TINYINT(-6, "TINYINT", "Byte", null),
    SMALLINT(5, "SMALLINT", "Integer", null),
    INTEGER(4, "INTEGER", "Integer", null),
    BIGINT(-5, "BIGINT", "Long", null),
    FLOAT(6, "FLOAT", "Double", null),
    REAL(7, "REAL", "Float", null),
    DOUBLE(8, "DOUBLE", "Double", null),
    NUMERIC(2, "NUMERIC", "BigDecimal", "java.math.BigDecimal"),
    DECIMAL(3, "DECIMAL", "BigDecimal", "java.math.BigDecimal"),
    NUMBER(2, "NUMBER", "BigDecimal", "java.math.BigDecimal"),
    CHAR(1, "CHAR", "String", null),
    VARCHAR(12, "VARCHAR", "String", null),
    VARCHAR2(12, "VARCHAR2", "String", null),
    NVARCHAR(-9, "NVARCHAR", "String", null),
    NVARCHAR2(-9, "NVARCHAR2", "String", null),
    LONG(-1, "LONG", "String", null),
    LONGVARCHAR(-1, "LONGVARCHAR", "String", null),
    DATE(91, "DATE", "Date", "java.util.Date"),
    TIME(92, "TIME", "Date", "java.util.Date"),
    TIMESTAMP(93, "TIMESTAMP", "Date", "java.util.Date"),
    TIMESTAMPNS(-100, "TIMESTAMPNS", "Date", "java.util.Date"),
    TIMESTAMPTZ(-101, "TIMESTAMPTZ", "Date", "java.util.Date"),
    TIMESTAMPLTZ(-102, "TIMESTAMPLTZ", "Date", "java.util.Date"),
    BINARY(-2, "BINARY", "byte[]", null),
    VARBINARY(-3, "VARBINARY", "byte[]", null),
    LONGVARBINARY(-4, "LONGVARBINARY", "byte[]", null),
    CLOB(2005, "CLOB", "Clob", "java.sql.Clob"),
    BLOB(2004, "BLOB", "Blob", "java.sql.Blob"),
    ARRAY(2003, "ARRAY", "Array", "java.sql.Array"),
    STRUCT(2002, "STRUCT", "Struct", "java.sql.Struct"),
    REF(2006, "REF", "Ref", "java.sql.Ref"),
    DATALINK(70, "DATALINK", "URL", "java.net.URL"),
    FIXED_CHAR(999, "FIXED_CHAR", "String", null),
    BINARY_FLOAT(100, "BINARY_FLOAT", "Double", null),
    BINARY_DOUBLE(101, "BINARY_DOUBLE", "Double", null);

    private int type;

    private String jdbcType;

    private String javaType;

    private String importClass;

    private OracleTypeEnum(int type, String jdbcType, String javaType, String importClass) {
        this.type = type;
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.importClass = importClass;
    }

    /**
     *
     * @description 根据类型值获取类型信息
     * @author bazhandao
     * @date 2018-10-23
     * @param type
     * @return
     */
    public static OracleTypeEnum getByType(int type) {
        OracleTypeEnum [] arr = values();
        for(OracleTypeEnum e : arr) {
            if(type == e.type) {
                return e;
            }
        }
        return null;
    }

    /**
     *
     * @description 根据jdbc类型获取类型信息
     * @author bazhandao
     * @date 2018-10-23
     * @param jdbcType
     * @return
     */
    public static OracleTypeEnum getByJdbcType(String jdbcType) {
        OracleTypeEnum [] arr = values();
        for(OracleTypeEnum e : arr) {
            if(e.jdbcType.equalsIgnoreCase(jdbcType)) {
                return e;
            }
        }
        return null;
    }

    /**
     * @description 根据java类型获取类型信息
     * @author bazhandao
     * @date 2018-10-23
     * @param javaType
     * @return
     */
    public static OracleTypeEnum getByJavaType(String javaType) {
        OracleTypeEnum [] arr = values();
        for(OracleTypeEnum e : arr) {
            if(e.javaType.equalsIgnoreCase(javaType)) {
                return e;
            }
        }
        return null;
    }

    public String getJdbcType() {
        if(this.jdbcType.equals("VARCHAR2")) {
            return "VARCHAR";
        } else if(this.jdbcType.equals("NVARCHAR2")) {
            return "NVARCHAR";
        } else if(this.jdbcType.equals("NUMBER")) {
            return "NUMERIC";
        } else if(this.jdbcType.equals("DATE")) {
            return "TIMESTAMP";
        }
        return this.jdbcType;
    }

}
