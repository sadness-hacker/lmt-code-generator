package com.lmt.code.util;

import com.lmt.code.bean.TableBean;
import com.lmt.code.context.GeneratorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @description velocity工具类
 *
 * @author bazhandao
 * @date 2018/11/2 16:33
 * @since JDK1.8
 */
@Slf4j
public class VelocityUtil {

    private static VelocityEngine velocityEngine = initVelocityEngine();

    public static VelocityEngine initVelocityEngine(){
        //初始化参数
        Properties properties = new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //实例化一个VelocityEngine对象
        return new VelocityEngine(properties);
    }

    /**
     * 创建VelocityContext
      * @param generatorContext
     * @param tb
     * @return
     */
    public static VelocityContext buildVelocityContext(GeneratorContext generatorContext, TableBean tb) {
        VelocityContext context = new VelocityContext();
        context.put("tableBean", tb);
        context.put("ignoreSchema", generatorContext.isIgnoreSchema());
        context.put("dbType", generatorContext.getDbType());
        context.put("requestMappingPrefix", generatorContext.getControllerRequestMappingPrefix());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        context.put("date", sdf.format(new Date()));
        context.put("context", generatorContext);
        return context;
    }

    /**
     * 生成代码
     * @param vm
     * @param project
     * @param classFullName
     * @param generatorContext
     * @param tb
     */
    public static void generateCode(String vm, String project, String classFullName, GeneratorContext generatorContext, TableBean tb) {
        VelocityContext context = buildVelocityContext(generatorContext, tb);
        String path = FileUtil.toFilePath(generatorContext.getWorkspace(), project, classFullName);
        generateCode(vm, context, path);
    }

    /**
     * 生成代码
     * @param vm
     * @param context
     * @param path
     */
    private static void generateCode(String vm, VelocityContext context, String path) {
        try(FileWriter fw = new FileWriter(path);) {
            velocityEngine.mergeTemplate(vm, "UTF-8", context, fw);
        } catch (Exception e) {
            log.error("Velocity合成代码出错！！！vm={}" + vm, e);
        }
    }



}
