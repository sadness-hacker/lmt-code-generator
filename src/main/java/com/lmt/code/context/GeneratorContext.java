package com.lmt.code.context;

import com.lmt.code.exception.BasicException;
import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @description 代码生成器上下文环境
 *
 * @author bazhandao
 * @date 2018/11/2 09:52
 * @since JDK1.8
 */
@Getter
@Setter
public class GeneratorContext {
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * 数据库url
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库名或schema名
     */
    private String schema;
    /**
     * 表前缀
     */
    private String tablePrefix;
    /**
     * 字段名前缀
     */
    private String columnPrefix;
    /**
     * 包括的表
     */
    private String includeTables;
    /**
     * 是否忽略schema
     */
    private boolean ignoreSchema;
    /**
     * 工作空间目录
     */
    private String workspace;
    /**
     * 基础包名
     */
    private String basePackage;

//    code.generator.entity.package=com.baosight.bams.sj.aa.entity
//    code.generator.basic.mapper.package=com.baosight.bams.sj.aa.basic.mapper
//    code.generator.mapper.package=com.baosight.bams.sj.aa.mapper
//    code.generator.mapper.xml.package=sqlmapper
//    code.generator.basic.service.package=com.baosight.bams.sj.aa.basic.service
//    code.generator.service.package=com.baosight.bams.sj.aa.mapper
//    code.generator.controller.package=com.baosight.bams.sj.aa.controller
    /**
     * 实体包名
     */
    private String entityPakcage;
    /**
     * 基础mapper包名
     */
    private String basicMapperPackage;
    /**
     * 扩展mapper包名
     */
    private String mapperPakcage;
    /**
     * mapper xml包名
     */
    private String mapperXmlPackage;
    /**
     * 基础service包名
     */
    private String basicServicePackage;
    /**
     * 扩展service包名
     */
    private String servicePackage;
    /**
     * controller包名
     */
    private String controllerPackage;

    /**
     * 需要继承的基础实体类
     */
    private String basicEntityClass;
    /**
     * 忽略的字段
     */
    private String engityIgnoreColumns;
    /**
     * 是否启用mybatis-page-helper
     */
    private boolean mybatisPagehelperEnable;
    /**
     * 是否启用lombok
     */
    private boolean lombokEnable;

    /**
     * 根据配置文件路径创建代码生成器执行环境
     * @param configFilePath
     * @return
     */
    public static GeneratorContext build(String configFilePath) {
        try {
            Properties property = new Properties();
            property.load(new FileInputStream(configFilePath));
            return build(property);
        } catch (Exception e) {
            throw new BasicException("2202", "加载配置文件出错!configFile=" + configFilePath);
        }
    }

    /**
     * 根据配置map生成GeneratorContext
     * @param configMap
     * @return
     */
    public static GeneratorContext build(Map<Object, Object> configMap) {
        GeneratorContext context = new GeneratorContext();
        context.setDbType(getString(configMap,"spring.datasource.db.type"));
        context.setUrl(getString(configMap,"spring.datasource.url"));
        context.setUsername(getString(configMap,"spring.datasource.username"));
        context.setPassword(getString(configMap,"spring.datasource.password"));
        context.setTablePrefix(getString(configMap,"code.generator.table-prefix"));
        context.setColumnPrefix(getString(configMap,"code.generator.column-prefix"));
        context.setIncludeTables(getString(configMap,"code.generator.include.tables"));
        context.setIgnoreSchema(getBoolean(configMap, "code.generator.ingore-schename"));
        context.setWorkspace(getString(configMap, "code.generator.workspace"));
        context.setBasePackage(getString(configMap, "code.generator.base-package"));
        context.setBasicEntityClass(getString(configMap, "code.generator.basic-entity-class"));
        context.setEngityIgnoreColumns(getString(configMap, "code.generator.entity.ignore-columns"));
        context.setMybatisPagehelperEnable(getBoolean(configMap, "code.generator.mybatis-pagehelper.enable"));
        context.setLombokEnable(getBoolean(configMap, "code.generator.lombok.enable"));
        context.setSchema(getString(configMap, "spring.datasource.schema"));
        context.setEntityPakcage(getString(configMap, "code.generator.entity.package"));
        context.setBasicMapperPackage(getString(configMap, "code.generator.basic.mapper.package"));
        context.setMapperPakcage(getString(configMap, "code.generator.mapper.package"));
        context.setMapperXmlPackage(getString(configMap, "code.generator.mapper.xml.package"));
        context.setBasicServicePackage(getString(configMap, "code.generator.basic.service.package"));
        context.setServicePackage(getString(configMap, "code.generator.service.package"));
        context.setControllerPackage(getString(configMap, "code.generator.controller.package"));
        return context;
    }

    private static String getString(Map<Object, Object> map, Object key) {
        return String.valueOf(map.get(key));
    }

    private static boolean getBoolean(Map<Object, Object> map, Object key) {
        Boolean b = Boolean.valueOf(getString(map, key));
        return b == null ? false : b;
    }

}