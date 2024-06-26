package com.chaoyue.haodebar.biz.example.controller;

import com.chaoyue.common.base.Result;
import com.chaoyue.haodebar.api.service.ExampleService;
import com.chaoyue.haodebar.biz.example.api.ExampleControllerApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/17 16:33
 * @version: version 1.0
 * @dec: 描述信息
 */
@RestController()
@RequestMapping("/example")
public class ExampleController implements ExampleControllerApi {
    @Resource
    private ExampleService exampleService;

    @Override
    @PostMapping("/testNacos")
    public Result test() {
        return exampleService.testMethod();
    }

    @Override
    @PostMapping("/testAdvice")
    public Result testAdvice() {
        return exampleService.testMethodAdvice();
    }

    @Override
    @PostMapping("/testIpAccess")

    public Result testIpAccess() {
        return null;
    }
}
