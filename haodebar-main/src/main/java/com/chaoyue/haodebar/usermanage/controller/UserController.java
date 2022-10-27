package com.chaoyue.haodebar.usermanage.controller;

import com.chaoyue.common.utils.Result;
import com.chaoyue.haodebar.usermanage.api.UserControllerApi;
import com.chaoyue.haodebar.usermanage.domain.UserReqDto;
import com.chaoyue.haodebar.usermanage.model.UserModel;
import com.chaoyue.haodebar.usermanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
@Slf4j
@RestController("/userManager")
public class UserController implements UserControllerApi {

    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    @PostMapping ("/saveData")
    public Result<UserModel> saveData() {
        log.info("xzc");
        UserModel model = new UserModel();
        model.setVersion("版本");
        model.setUserName("许智超");
        model.setEmail("xzc@qq.com");
        model.setPhone("18810440946");
        //redisTemplate.opsForValue().set("xzctset",1);
        userService.save(model);
        return Result.createOK(model);
    }

    @Override
    @PostMapping("deleteUser")
    public Result<Boolean> deleteUser(UserReqDto userReqDto) {
        log.info("enter deleteUser controller,param={}",userReqDto);
        return Result.createOK(userService.removeById(userReqDto.getUserId()));
    }

}
