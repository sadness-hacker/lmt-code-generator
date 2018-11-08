package com.lmt.code.test;

import com.lmt.code.util.SQLUtil;
import org.junit.Test;

/**
 * @description SQLUtil测试类
 *
 * @author bazhandao
 * @date 2018/11/2 11:57
 * @since JDK1.8
 */
public class SQLUtilTest {

    @Test
    public void getSql() {
        System.out.println(SQLUtil.getSql(null,"oracle", "selectTable"));
    }

}
