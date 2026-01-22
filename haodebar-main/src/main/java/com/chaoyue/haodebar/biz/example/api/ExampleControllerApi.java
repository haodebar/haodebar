package com.chaoyue.haodebar.biz.example.api;

import com.chaoyue.common.annotation.access.IpAccessPermission;
import com.chaoyue.common.base.Result;
import com.chaoyue.haodebar.constant.IpAccessKeyConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/17 16:28
 * @version: version 1.0
 * @dec: 这是个example
 */
@Tag(name = "样例管理", description = "样例管理相关接口")
public interface ExampleControllerApi {
    @Operation(summary = "测试使用",description = "这是测试接口")
    Result test();

    @Operation(summary = "测试异常切面",description = "这是测试接口")
    Result testAdvice();

    @Operation(summary = "测试IP黑白名单",description = "这是测试接口")
    @IpAccessPermission(whiteIpsKey = IpAccessKeyConstant.DEFAULT_WHITE,blackIpsKey=IpAccessKeyConstant.DEFAULT_BLACK)
    Result testIpAccess();
}
