package com.chaoyue.haodebar.biz.usermanage.api;

import com.chaoyue.common.annotation.access.IpAccessPermission;
import com.chaoyue.common.base.Result;
import com.chaoyue.haodebar.api.model.UserModel;
import com.chaoyue.haodebar.biz.usermanage.domain.UserReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

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
    /**
     * 用户新增接口
     * @return
     */
    @ApiOperation(value = "用户新增",notes = "用户新增接口")
    Result<UserModel> saveData();

    /**
     * 用户删除接口
     * @param userReqDto
     * @return
     */
    @ApiOperation(value = "用户删除",notes = "用户删除接口")
    Result<Boolean> deleteUser(@RequestBody UserReqDto userReqDto);

    /**
     * 用户修改接口
     * @param userReqDto
     * @return
     */
    @ApiOperation(value = "用户修改",notes = "用户修改接口")
    Result<Boolean> modifyUser(@RequestBody UserReqDto userReqDto);
}
