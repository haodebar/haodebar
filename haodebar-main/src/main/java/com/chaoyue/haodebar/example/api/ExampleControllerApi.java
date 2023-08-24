package com.chaoyue.haodebar.example.api;

import com.chaoyue.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/17 16:28
 * @version: version 1.0
 * @dec: 这是个example
 */
@Api(tags = "样例管理")
public interface ExampleControllerApi {
    @ApiOperation(value = "测试使用",notes = "这是测试接口")
    Result test();
}
