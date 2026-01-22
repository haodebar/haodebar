package com.chaoyue.haodebar.biz.usermanage.api;

import com.chaoyue.common.base.Result;
import com.chaoyue.haodebar.api.model.UserModel;
import com.chaoyue.haodebar.biz.usermanage.domain.UserReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
@Tag(name = "用户管理", description = "用户相关接口")
public interface UserControllerApi {
    /**
     * 用户新增接口
     * @return
     */
    @Operation(summary = "用户新增",description = "用户新增接口")
    Result<UserModel> saveData();

    /**
     * 用户删除接口
     * @param userReqDto
     * @return
     */
    @Operation(summary = "用户删除",description = "用户删除接口")
    Result<Boolean> deleteUser(@RequestBody UserReqDto userReqDto);

    /**
     * 用户修改接口
     * @param userReqDto
     * @return
     */
    @Operation(summary = "用户修改",description = "用户修改接口")
    Result<Boolean> modifyUser(@RequestBody UserReqDto userReqDto);
}
