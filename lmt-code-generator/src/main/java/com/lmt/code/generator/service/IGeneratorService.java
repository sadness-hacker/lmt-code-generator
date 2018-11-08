/**
 * Copyright (c) 2018 XiaoXuanKeJi All Rights Reserved.
 */
package com.lmt.code.generator.service;

import com.lmt.code.bean.TableBean;

import java.util.List;

/**
 * 
 * @description 代码生成器Service接口
 * @author bazhandao
 * @date 2018年10月22日 上午11:43:04
 * @since JDK 1.8
 * 
 */
public interface IGeneratorService {

    /**
     * @description 获取符合条件的表TableBean
     * @author bazhandao
     * @date 2018-11-02
     * @return List<TableBean>
     */
    public List<TableBean> listTable();

    /**
     * @description 生成代码
     * @author bazhandao
     * @date 2018-11-02
     * @return List<TableBean>
     */
    public void generateCoce();

}
