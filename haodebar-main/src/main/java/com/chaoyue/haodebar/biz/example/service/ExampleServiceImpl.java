package com.chaoyue.haodebar.biz.example.service;

import com.chaoyue.common.base.service.impl.BaseServiceImpl;
import com.chaoyue.common.base.Result;
import com.chaoyue.common.constant.ResultCodeEnum;
import com.chaoyue.common.exception.BusinessException;
import com.chaoyue.haodebar.api.model.ExampleModel;
import com.chaoyue.haodebar.api.service.ExampleService;
import com.chaoyue.haodebar.biz.example.dao.ExampleDao;
import com.chaoyue.haodebar.tool.BizConfigUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/17 16:36
 * @version: version 1.0
 * @dec: 描述信息
 */
@Service
public class ExampleServiceImpl extends BaseServiceImpl<ExampleDao, ExampleModel> implements ExampleService {

    @Value(value="test")
    private String testValue;

    @Resource
    private BizConfigUtils bizConfigUtils;

    @Override
    public Result testMethod() {
        return Result.createOk(bizConfigUtils.getStringValue("test"));
    }

    @Override
    public Result testMethodAdvice() {
        throw  new BusinessException(ResultCodeEnum.FAILED.getCode(),ResultCodeEnum.FAILED.getMsg());
    }
}
