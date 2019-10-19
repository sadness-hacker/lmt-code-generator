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

    /**
     * 基础实体类名
     */
    private String basicEntityClass;

    /**
     * 基础实体类名
     */
    private String basicEntityClassShortName;

    /**
     * 实体包名
     */
    private String entityPakcage;

    /**
     * 基础mapper类名
     */
    private String basicMapperClassName;

    /**
     * 基础mapper类名简称
     */
    private String basicMapperClassShortName;

    /**
     * 扩展mapper包名
     */
    private String mapperPakcage;
    /**
     * mapper xml包名
     */
    private String mapperXmlPackage;
    /**
     * service接口包名
     */
    private String servicePackage;
    /**
     * service实现类包名
     */
    private String serviceImplPackage;
    /**
     * controller包名
     */
    private String controllerPackage;
    /**
     * controller前缀
     */
    private String controllerRequestMappingPrefix;

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
        context.setSchema(getString(configMap, "spring.datasource.schema"));
        context.setBasicEntityClass(getString(configMap, "code.generator.basic.entity.class"));
        String s = context.getBasicEntityClass();
        s = s.substring(s.lastIndexOf('.') + 1);
        context.setBasicEntityClassShortName(s);
        context.setEntityPakcage(getString(configMap, "code.generator.entity.package"));
        context.setBasicMapperClassName(getString(configMap, "code.generator.basic.mapper.class"));
        s = context.getBasicMapperClassName();
        s = s.substring(s.lastIndexOf('.') + 1);
        context.setBasicMapperClassShortName(s);
        context.setMapperPakcage(getString(configMap, "code.generator.mapper.package"));
        context.setMapperXmlPackage(getString(configMap, "code.generator.mapper.xml.package"));
        context.setServicePackage(getString(configMap, "code.generator.service.package"));
        context.setServiceImplPackage(getString(configMap, "code.generator.service.impl.package"));
        context.setControllerPackage(getString(configMap, "code.generator.controller.package"));
        context.setControllerRequestMappingPrefix(getString(configMap, "code.generator.controller.request-mapping-prefix"));
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
