/**
 * Copyright (c) 2018 XiaoXuanKeJi All Rights Reserved.
 */
package com.lmt.code.example.basic.mapper;

import com.lmt.code.example.model.Model;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @description 基础接口
 * @author bazhandao
 * @date 2018年10月22日 上午11:53:26
 * @since JDK 1.8
 * 
 */
public interface BasicModelMapper {
	
	/**
	 * 
	 * @description 插入记录
	 * @author bazhandao
	 * @date 2018年10月22日 下午12:00:03
	 * @param t
	 * @return
	 * 
	 */
	public int insert(Model model);
	
	/**
	 * 
	 * @description 批量插入记录
	 * @author bazhandao
	 * @date 2018年10月22日 下午12:00:41
	 * @param t
	 * @return
	 * 
	 */
	public int batchInsert(List<Model> modelList);
	
	/**
	 * 
	 * @description 根据id获取记录
	 * @author bazhandao
	 * @date 2018年10月22日 上午11:55:36
	 * @param id
	 * @return
	 * 
	 */
	public Model get(String id);

	/**
	 * 根据id列表查寻实体列表
	 * @param idList
	 * @return
	 */
	public List<Model> listByIdList(List<String> idList);

	/**
	 * 
	 * @description根据id删除记录
	 * @author bazhandao
	 * @date 2018年10月22日 上午11:57:34
	 * @param id
	 * @return
	 * 
	 */
	public int delete(String id);
	
	/**
	 * 
	 * @description根据id更新记录
	 * @author bazhandao
	 * @date 2018年10月22日 上午11:57:52
	 * @param t
	 * @return
	 * 
	 */
	public int update(Model model);
	
	/**
	 * 
	 * @description统计记录数
	 * @author bazhandao
	 * @date 2018年10月22日 上午11:58:49
	 * @param t
	 * @return
	 * 
	 */
	public long count(Model t);
	
	/**
	 * 
	 * @description 查寻记录列表
	 * @author bazhandao
	 * @date 2018年10月22日 上午11:56:13
	 * @param t
	 * @return
	 * 
	 */
	public List<Model> query(Model t);
	
	/**
	 * 
	 * @description 分页查寻
	 * @author bazhandao
	 * @date 2018年10月22日 上午11:59:13
	 * 
	 */
	public void queryByPage(@Param(value = "a") String a);
	
}
