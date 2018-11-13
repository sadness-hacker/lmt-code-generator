/**
 * Copyright (c) 2018 XiaoXuanKeJi All Rights Reserved.
 */
package com.lmt.code;

import com.lmt.code.context.GeneratorContext;
import com.lmt.code.generator.service.OracleGeneratorService;
import com.lmt.code.util.FileUtil;

/**
 * 
 * @description main方法生成代码
 * @author bazhandao
 * @date 2018年10月22日 上午11:35:41
 * @since JDK 1.8
 * 
 */
public class Main {

    public static void main(String [] args) {
        String path = Main.class.getClassLoader().getResource("application.properties").getPath();
        GeneratorContext generatorContext = GeneratorContext.build(path);
        OracleGeneratorService generatorService = new OracleGeneratorService();
        generatorService.setGeneratorContext(generatorContext);
        FileUtil.clear(generatorContext.getWorkspace());
        generatorService.generateCoce();
    }

}
