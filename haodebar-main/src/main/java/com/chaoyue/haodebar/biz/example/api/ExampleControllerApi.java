package com.chaoyue.haodebar.biz.example.api;

import com.chaoyue.common.annotation.access.IpAccessPermission;
import com.chaoyue.common.base.Result;
import com.chaoyue.haodebar.constant.IpAccessKeyConstant;
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

    @ApiOperation(value = "测试异常切面",notes = "这是测试接口")
    Result testAdvice();

    @ApiOperation(value = "测试IP黑白名单",notes = "这是测试接口")
    @IpAccessPermission(whiteIpsKey = IpAccessKeyConstant.DEFAULT_WHITE,blackIpsKey=IpAccessKeyConstant.DEFAULT_BLACK)
    Result testIpAccess();
}
