package com.lmt.code.test;

import com.lmt.code.context.GeneratorContext;
import com.lmt.code.generator.service.MySQLGeneratorService;
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
public class MysqlGenerateServiceTest {


    @Test
    public void generateCode() {
        String path = MysqlGenerateServiceTest.class.getClassLoader().getResource("application-mysql.properties").getPath();
        GeneratorContext generatorContext = GeneratorContext.build(path);
        MySQLGeneratorService generatorService = new MySQLGeneratorService();
        generatorService.setGeneratorContext(generatorContext);
        FileUtil.clear(generatorContext.getWorkspace());
        generatorService.generateCoce();
    }

}
