package com.chaoyue.haodebar.biz.login.api;

import com.chaoyue.common.base.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/8/18 16:54
 * @version: version 1.0
 * @dec: 描述信息
 */
@Tag(name = "用户登录", description = "用户登录相关接口")
public interface UserLoginControllerApi{
    @Operation(summary = "用户登录", description = "用户登录")
    Result login();

    @Operation(summary = "用户退出", description = "用户退出")
    Result logout();

    @Operation(summary = "邮件登录", description = "邮件登录")
    Result loginEmail();

    @Operation(summary = "手机号登录", description = "手机号登录")
    Result loginPhone();
}
