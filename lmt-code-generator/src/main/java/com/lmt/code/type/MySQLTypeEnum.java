package com.lmt.code.type;

import lombok.Getter;

@Getter
public enum MySQLTypeEnum {
//    VARCHAR	L+N	VARCHAR	java.lang.String	12
//    CHAR	N	CHAR	java.lang.String	1
//    BLOB	L+N	BLOB	java.lang.byte[]	-4
//    TEXT	65535	VARCHAR	java.lang.String	-1
//    INTEGER	4	INTEGER UNSIGNED	java.lang.Long	4
//    TINYINT	3	TINYINT UNSIGNED	java.lang.Integer	-6
//    SMALLINT	5	SMALLINT UNSIGNED	java.lang.Integer	5
//    MEDIUMINT	8	MEDIUMINT UNSIGNED	java.lang.Integer	4
//    BIT	1	BIT	java.lang.Boolean	-7
//    BIGINT	20	BIGINT UNSIGNED	java.math.BigInteger	-5
//    FLOAT	4+8	FLOAT	java.lang.Float	7
//    DOUBLE	22	DOUBLE	java.lang.Double	8
//    DECIMAL	11	DECIMAL	java.math.BigDecimal	3
//    BOOLEAN	1	同TINYINT
//    ID	11	PK (INTEGER UNSIGNED)	java.lang.Long	4
//    DATE	10	DATE	java.sql.Date	91
//    TIME	8	TIME	java.sql.Time	92
//    DATETIME	19	DATETIME	java.sql.Timestamp	93
//    TIMESTAMP	19	TIMESTAMP	java.sql.Timestamp	93
//    YEAR	4	YEAR	java.sql.Date	91

    VARCHAR(12, "VARCHAR", "String", null),
    VARBINARY(-3, "VARBINARY", "String", null),
    JSON(-1, "JSON", "String", null),
    CHAR(1,"CHAR","String", null),
    TINYBLOB(-3, "TINYBLOB", "byte[]", null),
    BLOB(-4, "BLOB", "byte[]", null),
    MEDIUMBLOB(-4, "MEDIUMBLOB", "byte[]", null),
    LONGBLOB(-4, "LONGBLOB", "byte[]", null),
    TINYTEXT(12, "TINYTEXT", "String", null),
    TEXT(-1, "TEXT", "String", null),
    MEDIUMTEXT(-1, "MEDIUMTEXT", "String", null),
    LONGTEXT(-1, "LONGTEXT", "String", null),
    ENUM(1, "ENUM", "String", null),
    SET(1, "SET", "String", null),
    INT(4, "INT", "Integer", null),
    INTEGER(4, "INTEGER", "Integer", null),
    INT_UNSIGNED(4, "INT UNSIGNED", "Long", null),
    TINYINT(-6, "TINYINT", "Integer", null),
    TINYINT_UNSIGNED(-6, "TINYINT UNSIGNED", "Integer", null),
    SMALLINT(5, "SMALLINT", "Integer", null),
    SMALLINT_UNSIGNED(5, "SMALLINT UNSIGNED", "Integer", null),
    MEDIUMINT(4, "MEDIUMINT", "Integer", null),
    MEDIUMINT_UNSIGNED(4, "MEDIUMINT UNSIGNED", "Integer", null),
    BIT(-7, "BIT", "Integer", null),
    BIGINT(-5, "BIGINT", "Long", null),
    BIGINT_UNSIGNED(-5, "BIGINT UNSIGNED", "java.math.BigInteger", "java.math.BigInteger"),
    FLOAT(7, "FLOAT", "Float", null),
    FLOAT_UNSIGNED(7, "FLOAT UNSIGNED", "Float", null),
    DOUBLE(8, "DOUBLE", "Double", null),
    DOUBLE_UNSIGNED(8, "DOUBLE UNSIGNED", "Double", null),
    NULL(0, "NULL", "Object" ,null),
    DECIMAL(3, "DECIMAL", "java.math.BigDecimal", "java.math.BigDecimal"),
    DECIMAL_UNSIGNED(3, "DECIMAL UNSIGNED", "java.math.BigDecimal", "java.math.BigDecimal"),
    BOOLEAN(-6, "BOOLEAN", "Integer", null),
    ID(4, "ID", "Integer", null),
    DATE(91, "DATE", "java.util.Date", "java.util.Date"),
    TIME(92, "TIME", "java.util.Date", "java.util.Date"),
    DATETIME(93, "DATETIME", "java.util.Date", "java.util.Date"),
    TIMESTAMP(93, "TIMESTAMP", "java.util.Date", "java.util.Date"),
    YEAR(91, "YEAR", "java.util.Date", "java.util.Date"),
    BINARY(-1, "BINARY", "byte[]", null),
    ;

    private int type;

    private String jdbcType;

    private String javaType;

    private String importClass;

    private MySQLTypeEnum(int type, String jdbcType, String javaType, String importClass) {
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
    public static MySQLTypeEnum getByType(int type) {
        MySQLTypeEnum [] arr = values();
        for(MySQLTypeEnum e : arr) {
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
    public static MySQLTypeEnum getByJdbcType(String jdbcType) {
        MySQLTypeEnum [] arr = values();
        for(MySQLTypeEnum e : arr) {
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
    public static MySQLTypeEnum getByJavaType(String javaType) {
        MySQLTypeEnum [] arr = values();
        for(MySQLTypeEnum e : arr) {
            if(e.javaType.equalsIgnoreCase(javaType)) {
                return e;
            }
        }
        return null;
    }

}
