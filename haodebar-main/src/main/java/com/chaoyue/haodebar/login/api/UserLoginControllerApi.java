package com.chaoyue.haodebar.login.api;

import com.chaoyue.common.utils.Result;
import com.chaoyue.haodebar.login.controller.UserLoginController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/8/18 16:54
 * @version: version 1.0
 * @dec: 描述信息
 */
@Api(tags = "账号登陆")
public interface UserLoginControllerApi{
    @ApiOperation(value = "用户登陆",notes = "用户登陆")
    Result login();

    @ApiOperation(value = "用户退出",notes = "用户退出")
    Result logout();
}
