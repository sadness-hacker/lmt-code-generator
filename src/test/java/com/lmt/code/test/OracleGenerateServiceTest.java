package com.lmt.code.test;

import com.lmt.code.context.GeneratorContext;
import com.lmt.code.generator.service.OracleGeneratorService;
import com.lmt.code.util.FileUtil;
import org.junit.Test;

/**
 * @description for this class
 *
 * @author bazhandao
 * @date 2018/11/4 17:14
 * @since JDK1.8
 */
public class OracleGenerateServiceTest {


    @Test
    public void generateCode() {
        String path = OracleGenerateServiceTest.class.getClassLoader().getResource("application.properties").getPath();
        GeneratorContext generatorContext = GeneratorContext.build(path);
        OracleGeneratorService generatorService = new OracleGeneratorService();
        generatorService.setGeneratorContext(generatorContext);
        FileUtil.clear(generatorContext.getWorkspace());
        generatorService.generateCoce();
    }

}
