package com.lmt.code.example.controller;

import com.github.pagehelper.PageInfo;
import com.lmt.code.example.Result;
import com.lmt.code.example.model.Model;
import com.lmt.code.example.service.IModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/model")
public class ModelController {

    @Autowired
    private IModelService modelService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST, name = "新增Model")
    public Result<Integer> insert(Model model) {
        Result<Integer> result = new Result<>();
        try {
            int i = modelService.insert(model);
            result.setData(i);
        } catch (Exception e) {
            log.error("insert Model error! model={},{}", model, e);
            result.setError(9999, "添加失败!");
        }
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, name = "更新Model")
    public Result<Integer> update(Model model) {
        Result<Integer> result = new Result<>();
        try {
            int i = modelService.update(model);
            result.setData(i);
        } catch (Exception e) {
            log.error("update Model error! model={},{}", model, e);
            result.setError(9999, "更新失败!");
        }
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, name = "更新或新增Model")
    public Result<Integer> save(Model model) {
        Result<Integer> result = new Result<>();
        try {
            int i = modelService.saveOrUpdate(model);
            result.setData(i);
        } catch (Exception e) {
            log.error("save Model error! model={},{}", model, e);
            result.setError(9999, "保存失败!");
        }
        return result;
    }

    @RequestMapping(value = "/deleteByPk", method = RequestMethod.POST, name = "根据主键删除Model")
    public Result<Integer> deleteByPk(Model model) {
        Result<Integer> result = new Result<>();
        try {
            int i = modelService.delete(model);
            result.setData(i);
        } catch (Exception e) {
            log.error("delete Model by pk error! model={},{}", model, e);
            result.setError(9999, "删除失败!");
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, name = "根据实体条件删除Model")
    public Result<Integer> delete(Model model) {
        Result<Integer> result = new Result<>();
        try {
            int i = modelService.delete(model);
            result.setData(i);
        } catch (Exception e) {
            log.error("delete Model by where error! model={},{}", model, e);
            result.setError(9999, "删除失败!");
        }
        return result;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, name = "根据主键查寻Model")
    public Result<Model> get(String id) {
        Result<Model> result = new Result<>();
        try {
            Model model = modelService.get(id);
            result.setData(model);
        } catch (Exception e) {
            log.error("get model by pk error! model={},{}", id, e);
            result.setError(9999, "查寻失败!");
        }
        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET, name = "查寻Model列表")
    public Result<List<Model>> query(Model model) {
        Result<List<Model>> result = new Result<>();
        try {
            List<Model> modelList = modelService.query(model);
            result.setData(modelList);
        } catch (Exception e) {
            log.error("query Model error! model={},{}", model, e);
            result.setError(9999, "查寻失败!");
        }
        return result;
    }

    @RequestMapping(value = "/queryByPage", method = RequestMethod.GET, name = "分页查寻Model列表,并根据指定自动排序")
    public Result<PageInfo<Model>> queryByPage(
            @RequestParam(value = "data") Model model,
            @RequestParam(value = "pageNum") int pageNum,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortOrder") String sortOrder) {
        Result<PageInfo<Model>> result = new Result<>();
        try {
            PageInfo<Model> pageInfo = modelService.queryByPage(model, sortField , sortOrder, pageNum, pageSize);
            result.setData(pageInfo);
        } catch (Exception e) {
            log.error("query Model error! model={},pageNum={},pageSize={},sortField={},sortOrder={},{}", model, pageNum, pageSize, sortField, sortOrder, e);
            result.setError(9999, "查寻失败!");
        }
        return result;
    }

}
