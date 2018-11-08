package com.lmt.code.example.basic.service;

import com.github.pagehelper.PageInfo;
import com.lmt.code.example.model.Model;

import java.util.List;

/**
 * @description IBasicModelService服务接口定义
 *
 * @author bazhandao
 * @date 2018/11/1 15:34
 * @since JDK1.8
 */
public interface IBasicModelService {

    /**
     * @description 插入$tableBean.classNameFirstLower\到数据表
     * @param $tableBean.classNameFirstLower
     * @return int
     */
    public int insert(Model model);

    /**
     * @description 批量插入$tableBean.classNameFirstLower\到数据表
     * @param $tableBean.classNameFirstLower
     * @return int
     */
    public int batchInsert(List<Model> modelList);

    /**
     * @description 根据主键id查寻$tableBean.className\实体
     * @param $tableBean.idNameFirstLower
     * @return $tableBean.className
     */
    public Model get(String id);

    /**
     * @description 根据主键id列表查寻$tableBean.className\实体列表
     * @param $tableBean.idNameFirstLower\List
     * @return List<$tableBean.className>
     */
    public List<Model> listByIdList(List<String> idList);

    /**
     * @description 根据主键id删除记录
     * @param $tableBean.idNameFirstLower
     * @return int
     */
    public int delete(String id);

    /**
     * @description 根据实体删除记录
     * @param $tableBean.classNameFirstLower 不能所有字段都为空，否则sql执行出错
     * @return int
     */
    public int delete(Model model);

    /**
     * @description 根据主键id更新记录
     * @param $tableBean.classNameFirstLower    主键id字段不能为空
     * @return int
     */
    public int update(Model model);


    /**
     * @description 根据主实体统计记录数
     * @param $tableBean.classNameFirstLower
     * @return int
     */
    public long count(Model model);

    /**
     * @description 根据主实体查询记录
     * @param $tableBean.classNameFirstLower
     * @return int
     */
    public List<Model> query(Model model);

    /**
     * @description 根据主实体查询记录，根据指定的字段进行排序
     * @param $tableBean.classNameFirstLower
     * @param sortField 排序字段(可空)
     * @param sortOrder asc或desc(可空)
     * @return int
     */
    public List<Model> queryOrderBy(Model model, String sortField, String sortOrder);

    /**
     * 更新或保存
     * @param model
     * @return
     */
    public int saveOrUpdate(Model model);

    /**
     * 分页查寻
     * @param model
     * @param sortField
     * @param sortOrder
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Model> queryByPage(Model model, String sortField, String sortOrder, int pageNum, int pageSize);

}
