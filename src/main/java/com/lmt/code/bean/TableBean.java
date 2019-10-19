package com.lmt.code.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @description 数据表对应的bean信息
 *
 * @author bazhandao
 * @date 2018/11/1 13:19
 * @since JDK1.8
 */
@ToString(callSuper = true)
@Setter
@Getter
public class TableBean implements Serializable {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 基础实体类名
     */
    private String basicEntityClassName;

    /**
     * 基础实体类名简称
     */
    private String basicEntityClassShortName;

    /**
     * 实体包名
     */
    private String entityPackage;
    /**
     * 表对应的实体类简称
     */
    private String entityClassName;
    /**
     * 表对应的实体类全名
     */
    private String entityClassFullName;
    /**
     * 表对应的实体类简称首字母小写
     */
    private String entityClassNameFirstLower;
    /**
     * 类名全小写
     */
    private String entityClassNameLower;

    /**
     * 基础mapper类名
     */
    private String basicMapperClassName;

    /**
     * 基础mapper类名简称
     */
    private String basicMapperClassShortName;

    /**
     * mapper名简称、全称、首字母小写
     */
    private String mapperPackage;
    private String mapperClassName;
    private String mapperClassFullName;
    private String mapperClassNameFirstLower;

    /**
     * service接口名
     */
    private String serviceApiPackage;
    private String serviceApiClassName;
    private String serviceApiClassFullName;
    private String serviceApiClassNameFirstLower;

    /**
     * service名简称、全称
     */
    private String servicePackage;
    private String serviceClassName;
    private String serviceClassFullName;
    private String serviceClassNameFirstLower;

    /**
     * controller名简称、全称
     */
    private String controllerPackage;
    private String controllerClassName;
    private String controllerClassFullName;

    /**
     * id字段bean
     */
    private List<ColumnBean> pkColumnBeanList;

    /**
     * 主键列
     */
    private ColumnBean pkColumnBean;

    /**
     * id字段需要导入的类
     */
    private Set<String> pkColumnImportClassSet;

    /**
     * 表包含的所有字段列表
     */
    private List<ColumnBean> columnBeanList;

    /**
     * 需要导入的类列表
     */
    private Set<String> importClassSet;

}
