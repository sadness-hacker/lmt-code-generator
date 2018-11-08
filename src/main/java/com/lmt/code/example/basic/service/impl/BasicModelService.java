package com.lmt.code.example.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmt.code.example.basic.mapper.BasicModelMapper;
import com.lmt.code.example.basic.service.IBasicModelService;
import com.lmt.code.example.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BasicModelService implements IBasicModelService {

    @Autowired
    private BasicModelMapper basicModelMapper;

    /**
     * @param model
     * @return int
     * @description 插入$tableBean.classNameFirstLower\到数据表
     */
    public int insert(Model model) {
        return basicModelMapper.insert(model);
    }

    /**
     * @param modelList
     * @return int
     * @description 批量插入$tableBean.classNameFirstLower\到数据表
     */
    public int batchInsert(List<Model> modelList) {
        return basicModelMapper.batchInsert(modelList);
    }

    /**
     * @param id
     * @return $tableBean.className
     * @description 根据主键id查寻$tableBean.className\实体
     */
    public Model get(String id) {
        return basicModelMapper.get(id);
    }

    /**
     * @param idList
     * @return List<$tableBean.className>
     * @description 根据主键id列表查寻$tableBean.className\实体列表
     */
    public List<Model> listByIdList(List<String> idList) {
        return basicModelMapper.listByIdList(idList);
    }

    /**
     * @param id
     * @return int
     * @description 根据主键id删除记录
     */
    public int delete(String id) {
        return basicModelMapper.delete(id);
    }

    /**
     * @param model
     * @return int
     * @description 根据实体删除记录
     */
    public int delete(Model model) {
        return basicModelMapper.delete(null);
    }

    /**
     * @param model
     * @return int
     * @description 根据主键id更新记录
     */
    public int update(Model model) {
        return basicModelMapper.update(model);
    }

    /**
     * @param model
     * @return int
     * @description 根据主实体统计记录数
     */
    public long count(Model model) {
        return basicModelMapper.count(model);
    }

    /**
     * @param model
     * @return int
     * @description 根据主实体查询记录
     */
    public List<Model> query(Model model) {
        return basicModelMapper.query(model);
    }

    /**
     * @param model
     * @param sortField 排序字段(可空)
     * @param sortOrder asc或desc(可空)
     * @return int
     * @description 根据主实体查询记录，根据指定的字段进行排序
     */
    public List<Model> queryOrderBy(Model model, String sortField, String sortOrder) {
        return null;
    }

    /**
     * 更新或保存
     *
     * @param model
     * @return
     */
    public int saveOrUpdate(Model model) {
        return 0;
    }

    /**
     * 分页查寻
     *
     * @param model
     * @param sortField
     * @param sortOrder
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Model> queryByPage(Model model, String sortField, String sortOrder, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Model> list = queryOrderBy(model, sortField, sortOrder);
        return new PageInfo<Model>(list);
    }
}
