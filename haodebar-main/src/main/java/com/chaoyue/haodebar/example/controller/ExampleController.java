package com.chaoyue.haodebar.example.controller;

import com.chaoyue.common.utils.Result;
import com.chaoyue.haodebar.api.service.ExampleService;
import com.chaoyue.haodebar.example.api.ExampleControllerApi;
import org.springframework.web.bind.annotation.PostMapping;
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
@RestController("/example")
public class ExampleController implements ExampleControllerApi {
    @Resource
    private ExampleService exampleService;

    @Override
    @PostMapping("/testNacos")
    public Result test() {
        return exampleService.testMethod();
    }
}
