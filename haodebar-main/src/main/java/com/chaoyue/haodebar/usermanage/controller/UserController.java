package com.chaoyue.haodebar.usermanage.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chaoyue.common.utils.Result;
import com.chaoyue.haodebar.usermanage.api.UserControllerApi;
import com.chaoyue.haodebar.usermanage.domain.UserReqDto;
import com.chaoyue.haodebar.usermanage.model.UserModel;
import com.chaoyue.haodebar.usermanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Value("${test.name}")
    private String userName;

    @Override
    @PostMapping ("/saveData")
    public Result<UserModel> saveData() {
        log.info(userName);
        UserModel model = new UserModel();
        model.setVersion("版本");
        model.setUserName("许智超");
        model.setEmail("xzc@qq.com");
        model.setPhone("18810440946");
        redisTemplate.opsForValue().set("xzctset",1);
        userService.save(model);
        return Result.createOK(model);
    }

    @Override
    @PostMapping("deleteUser")
    public Result<Boolean> deleteUser(@RequestBody UserReqDto userReqDto) {
        log.info("enter deleteUser controller,param={}",userReqDto);
        return Result.createOK(userService.removeById(userReqDto.getUserId()));
    }

    @Override
    @PostMapping("modifyUser")
    public Result<Boolean> modifyUser(@RequestBody UserReqDto userReqDto) {
        log.info("enter modifyUser controller,param={}",userReqDto);
        return null;
    }
}
