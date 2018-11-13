/**
 * Copyright (c) 2018 XiaoXuanKeJi All Rights Reserved.
 */
package com.lmt.code.generator.service;

import com.lmt.code.bean.ColumnBean;
import com.lmt.code.bean.TableBean;
import com.lmt.code.context.GeneratorContext;
import com.lmt.code.exception.BasicException;
import com.lmt.code.util.JdbcUtil;
import com.lmt.code.util.SQLUtil;
import com.lmt.code.util.StringUtil;
import com.lmt.code.util.VelocityUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * 
 * @description
 * @author bazhandao
 * @date 2018年10月22日 上午11:49:05
 * @since JDK 1.8
 * 
 */
@Slf4j
@Getter
@Setter
public abstract class AbstractGeneratorService implements IGeneratorService{

    /**
     * 代码生成器上下文环境
     */
    private GeneratorContext generatorContext;

    /**
     * 获取数据库类型
     * @author bazhandap
     * @date 2018-11-02
     * @return
     */
    public abstract String getDbType();
    /**
     * 获取数据库驱动名
     * @author bazhandap
     * @date 2018-11-02
     * @return
     */
    public abstract String getDriverClassName();
    /**
     * ResultSet创建TableBean(获取表名，表注释字段)
     * @author bazhandap
     * @date 2018-11-02
     * @return
     */
    public abstract TableBean buildTableBean(ResultSet rs) throws Exception;
    /**
     * ResultSet创建ColumnBean(获取字段名，表名，字段注释，字段类型，字段长度等)
     * @author bazhandap
     * @date 2018-11-02
     * @return
     */
    public abstract ColumnBean buildColumnBean(ResultSet rs) throws Exception;

    /**
     * ResultSet创建主键的ColumnBean(获取字段名，表名)
     * @author bazhandap
     * @date 2018-11-02
     * @return
     */
    public ColumnBean buildPKColumnBean(ResultSet rs) throws Exception {
        ColumnBean cb = new ColumnBean();
        String tableName = rs.getString("table_name");
        String columnName = rs.getString("column_name");
        cb.setTableName(tableName);
        cb.setColumnName(columnName);
        return cb;
    };

    /**
     * 获取jdbc链接
     * @return
     */
    public Connection getConnection() {
        return JdbcUtil.getConnection(getDriverClassName(), generatorContext.getUrl(), generatorContext.getUsername(), generatorContext.getPassword());
    }

    /**
     * 关闭jdbc链接
     * @param conn
     */
    public void closeConnection(Connection conn) {
        JdbcUtil.closeConnection(conn);
    }

    /**
     * 关闭jdbc Statement
     * @param stat
     */
    public void closeStatement(Statement stat) {
        JdbcUtil.closeStatement(stat);
    }

    /**
     * 关闭jdbc ResultSet
     * @param rs
     */
    public void closeResultSet(ResultSet rs) {
        JdbcUtil.closeResultSet(rs);
    }

    /**
     * @return List<TableBean>
     * @description 获取符合条件的表TableBean
     * @author bazhandao
     * @date 2018-11-02
     */
    @Override
    public List<TableBean> listTable() {
        List<TableBean> list = new ArrayList<>();
        Map<String, TableBean> tableBeanMap = new HashMap<>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = SQLUtil.getSql(generatorContext, getDbType(), "selectTable");
            log.info("获取符合条件的表:sql={}", sql);
            conn = getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()) {
                TableBean tb = buildTableBean(rs);
                tableBeanMap.put(tb.getTableName(), tb);
                list.add(tb);
            }
            //补充完善tableBean信息
            build(tableBeanMap);
        } catch (Exception e) {
            log.error("获取符合条件的表TableBean异常", e);
            throw new BasicException("2000", "获取符合条件的表TableBean异常", e);
        } finally {
            closeConnection(conn);
            closeStatement(stat);
            closeResultSet(rs);
        }
        return list;
    }

    /**
     * @return List<TableBean>
     * @description 获取符合条件的表字段ColumnBean
     * @author bazhandao
     * @date 2018-11-02
     */
    public List<ColumnBean> listColumn() {
        List<ColumnBean> list = new ArrayList<>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = SQLUtil.getSql(generatorContext, getDbType(), "selectColumn");
            log.info("获取符合条件的字段:sql={}", sql);
            conn = getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()) {
                ColumnBean cb = buildColumnBean(rs);
                list.add(cb);
            }
        } catch (Exception e) {
            log.error("获取符合条件的字段ColumnBean异常", e);
            throw new BasicException("2001", "获取符合条件的表ColumnBean异常", e);
        } finally {
            closeConnection(conn);
            closeStatement(stat);
            closeResultSet(rs);
        }
        return list;
    }

    /**
     * @return List<TableBean>
     * @description 获取符合条件的表主键字段ColumnBean（表名，字段名不可为空）
     * @author bazhandao
     * @date 2018-11-02
     */
    public List<ColumnBean> listPKColumn() {
        List<ColumnBean> list = new ArrayList<>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = SQLUtil.getSql(generatorContext, getDbType(), "selectPrimaryKey");
            log.info("获取主键:sql={}", sql);
            conn = getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()) {
                ColumnBean cb = buildPKColumnBean(rs);
                list.add(cb);
            }
        } catch (Exception e) {
            log.error("获取表主键ColumnBean异常", e);
            throw new BasicException("2002", "获取符合条件的表ColumnBean异常", e);
        } finally {
            closeConnection(conn);
            closeStatement(stat);
            closeResultSet(rs);
        }
        return list;
    }

    /**
     *
     * @param tableBeanMap
     */
    private void build(Map<String, TableBean> tableBeanMap) {
        //处理字段
        List<ColumnBean> columnList = listColumn();
        for(ColumnBean cb : columnList) {
            String tableName = cb.getTableName();
            TableBean tb = tableBeanMap.get(tableName);
            if(tb == null) continue;
            List<ColumnBean> tmpList = tb.getColumnBeanList();
            if(tmpList == null) {
                tmpList = new ArrayList<>();
                tb.setColumnBeanList(tmpList);
            }
            if(!containsColumnBean(tmpList, cb)) {
                tmpList.add(cb);
            }
        }

        //处理主键
        List<ColumnBean> pkList = listPKColumn();
        Map<String, Set<String>> pkColumnMap = new HashMap<>();
        for(ColumnBean cb : pkList) {
            Set<String> set = pkColumnMap.get(cb.getTableName());
            if(set == null) {
                set = new HashSet<>();
                pkColumnMap.put(cb.getTableName(), set);
            }
            set.add(cb.getColumnName());
        }
        //处理主键(这样处理可以保证顺序与DB一致)
        tableBeanMap.forEach((tableName, tb) -> {
            Set<String> set = pkColumnMap.get(tableName);
            if(set == null) {
                return;
            }
            List<ColumnBean> list = tb.getColumnBeanList();
            List<ColumnBean> tmpPkList = new ArrayList<>(set.size());
            list.forEach(c -> {
                if(set.contains(c.getColumnName())) {
                    tmpPkList.add(c);
                }
            });
            tb.setPkColumnBeanList(tmpPkList);
        });
    }

    /**
     * 判断ColumnBeanList中是否包含字段名相同的字段，包含返回true
     * @param list
     * @param bean
     * @return
     */
    private boolean containsColumnBean(List<ColumnBean> list, ColumnBean bean) {
        if(list == null || bean == null) {
            return false;
        }
        for (ColumnBean c : list) {
            if(c.getColumnName().equals(bean.getColumnName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @description 生成代码
     * @author bazhandao
     * @date 2018-11-02
     * @return List<TableBean>
     */
    @Override
    public void generateCoce() {
        List<TableBean> list = listFilterTableBean();
        list.forEach(tb -> {
            buildClassName(tb);
            generateEntity(tb);
            generateBasicMapper(tb);
            generateMapper(tb);
            generateBasicService(tb);
            genreateService(tb);
            genreateService(tb);
            generateController(tb);
        });
    }

    private void generateEntity(TableBean tb) {
        String project = "code-entity";
        String classFullName = tb.getEntityClassFullName().replace(".", "/") + ".java";
        String vm = "template/Entity.vm";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);
    }

    private void generateBasicMapper(TableBean tb) {
        String project = "code-basic-mapper";
        String classFullName = tb.getBasicMapperClassFullName().replace(".", "/") + ".java";
        String vm = "template/BasicMapper.vm";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);

        vm = "template/BasicMapperXml.vm";
        classFullName = generatorContext.getMapperXmlPackage().replace(".", "/") + "/basic/" + tb.getBasicMapperClassName() + ".xml";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);

    }

    private void generateMapper(TableBean tb) {
        String project = "code-mapper";
        String classFullName = tb.getMapperClassFullName().replace(".", "/") + ".java";
        String vm = "template/Mapper.vm";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);

        vm = "template/MapperXml.vm";
        classFullName = generatorContext.getMapperXmlPackage().replace(".", "/") + "/" + tb.getMapperClassName() + ".xml";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);

    }

    private void generateBasicService(TableBean tb) {
        String project = "code-basic-service-impl";
        String classFullName = tb.getBasicServiceClassFullName().replace(".", "/") + ".java";
        String vm = "template/BasicServiceImpl.vm";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);

    }

    private void genreateService(TableBean tb) {
        String project = "code-service-api";
        String classFullName = tb.getServiceApiClassFullName().replace(".", "/") + ".java";
        String vm = "template/Service.vm";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);

        project = "code-service-impl";
        classFullName = tb.getServiceClassFullName().replace(".", "/") + ".java";
        vm = "template/ServiceImpl.vm";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);

    }

    private void generateController(TableBean tb) {
        String project = "code-controller";
        String classFullName = tb.getControllerClassFullName().replace(".", "/") + ".java";
        String vm = "template/Controller.vm";
        VelocityUtil.generateCode(vm, project, classFullName, generatorContext, tb);

    }

    private List<TableBean> listFilterTableBean() {
        List<TableBean> list = listTable();
        String includeTables = generatorContext.getIncludeTables();
        if(StringUtils.isBlank(includeTables)) {
            return list;
        }
        String [] patterArr = includeTables.split(",");
        List<String> patterList = new ArrayList<>(patterArr.length);
        for(String pattern : patterArr) {
            if(StringUtils.isBlank(pattern)) {
                continue;
            }
            pattern = pattern.replace("*", "");
            patterList.add(pattern.toUpperCase());
        }
        List<TableBean> tmpList = new ArrayList<>();
        list.forEach(tb -> {
            String n = tb.getTableName();
            for(String s : patterList) {
                if(n.toUpperCase().startsWith(s)) {
                    tmpList.add(tb);
                }
            }
        });
        return tmpList;
    }

    private void buildClassName(TableBean tb) {
        String tableName = tb.getTableName();
        String tablePrefix = getGeneratorContext().getTablePrefix();
        if(StringUtils.isNotBlank(tablePrefix) && tableName.toUpperCase().startsWith(tablePrefix.toUpperCase())) {
            tableName = tableName.toLowerCase().replaceFirst(tablePrefix.toLowerCase(), "");
        }
        //实体类名
        String entityClassName = StringUtil.toClassName(tableName);
        tb.setEntityPackage(generatorContext.getEntityPakcage());
        tb.setEntityClassName(entityClassName);
        tb.setEntityClassNameFirstLower(StringUtil.lowerFirstChar(entityClassName));
        tb.setEntityClassFullName(tb.getEntityPackage() + "." + entityClassName);
        tb.setEntityClassNameLower(entityClassName.toLowerCase());

        //BasicMapper类名
        tb.setBasicMapperPackage(generatorContext.getBasicMapperPackage());
        tb.setBasicMapperClassName("Basic" + entityClassName + "Mapper");
        tb.setBasicMapperClassNameFirstLower(StringUtil.lowerFirstChar(tb.getBasicMapperClassName()));
        tb.setBasicMapperClassFullName(tb.getBasicMapperPackage() + "." + tb.getBasicMapperClassName());

        //Mapper类名
        tb.setMapperPackage(generatorContext.getMapperPakcage());
        tb.setMapperClassName(entityClassName + "Mapper");
        tb.setMapperClassNameFirstLower(StringUtil.lowerFirstChar(tb.getMapperClassName()));
        tb.setMapperClassFullName(tb.getMapperPackage() + "." + tb.getMapperClassName());

        //BasicService类名
        tb.setBasicServicePackage(generatorContext.getBasicServicePackage() + ".impl");
        tb.setBasicServiceClassName("Basic" + entityClassName + "ServiceImpl");
        tb.setBasicServiceClassNameFirstLower(StringUtil.lowerFirstChar(tb.getBasicServiceClassName()));
        tb.setBasicServiceClassFullName(tb.getBasicServicePackage() + "." + tb.getBasicServiceClassName());

        //IService类名
        tb.setServiceApiPackage(generatorContext.getServicePackage());
        tb.setServiceApiClassName("" + entityClassName + "Service");
        tb.setServiceApiClassFullName(tb.getServiceApiPackage() + "." + tb.getServiceApiClassName());
        tb.setServiceApiClassNameFirstLower(StringUtil.lowerFirstChar(tb.getServiceApiClassName()));

        //Service类名
        tb.setServicePackage(generatorContext.getServicePackage() + ".impl");
        tb.setServiceClassName(entityClassName + "ServiceImpl");
        tb.setServiceClassNameFirstLower(StringUtil.lowerFirstChar(tb.getServiceClassName()));
        tb.setServiceClassFullName(tb.getServicePackage() + "." + tb.getServiceClassName());

        //Controller类名
        tb.setControllerPackage(generatorContext.getControllerPackage());
        tb.setControllerClassName(entityClassName + "Controller");
        tb.setControllerClassFullName(tb.getControllerPackage() + "." + tb.getControllerClassName());

        List<ColumnBean> columnBeanList = tb.getColumnBeanList();
        Set<String> importClassSet = new HashSet<>();
        columnBeanList.forEach(cb -> {
            String columnName = cb.getColumnName();
            String columnPrefix = getGeneratorContext().getColumnPrefix();
            if(StringUtils.isNotBlank(columnPrefix) && columnName.toUpperCase().startsWith(columnPrefix.toUpperCase())) {
                columnName = columnName.toLowerCase().replaceFirst(columnPrefix.toLowerCase(), "");
            }
            String javaName = StringUtil.toClassName(columnName);
            cb.setJavaNameFirstUpper(javaName);
            cb.setJavaName(StringUtil.lowerFirstChar(javaName));
            if(StringUtils.isNotBlank(cb.getImportClass())) {
                importClassSet.add(cb.getImportClass());
            }
        });
        tb.setImportClassSet(importClassSet);

        Set<String> pkImportClassSet = new HashSet<>();
        List<ColumnBean> pkList = tb.getPkColumnBeanList();
        pkList.forEach(cb -> {
            if(StringUtils.isNotBlank(cb.getImportClass())) {
                pkImportClassSet.add(cb.getImportClass());
            }
        });
        tb.setPkColumnImportClassSet(pkImportClassSet);
        if(pkList.size() != 1) {
            throw new BasicException("9988", "不支持多主键,请使用代理主键tableName=" + tb.getTableName());
        }
        tb.setPkColumnBean(pkList.get(0));
    }

}
