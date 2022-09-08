package com.chaoyue.haodebar.usermanage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Date:2019/12/5
 * Create by:xuzhichao
 * Describe:
 */
@Api(tags = "用户管理")
public interface UserControllerApi {
    @ApiOperation(value = "用户新增",notes = "用新增接口")
    void SaveData();
}
