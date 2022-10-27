package com.chaoyue.haodebar.usermanage.api;

import com.chaoyue.common.utils.Result;
import com.chaoyue.haodebar.usermanage.domain.UserReqDto;
import com.chaoyue.haodebar.usermanage.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
@Api(tags = "用户管理")
public interface UserControllerApi {
    @ApiOperation(value = "用户新增",notes = "用户新增接口")
    Result<UserModel> saveData();
    @ApiOperation(value = "用户删除",notes = "用户删除接口")
    Result<Boolean> deleteUser(UserReqDto userReqDto);
}
