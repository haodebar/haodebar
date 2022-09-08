package com.chaoyue.haodebar.usermanage.controller;

import com.chaoyue.haodebar.usermanage.api.UserControllerApi;
import com.chaoyue.haodebar.usermanage.model.UserModel;
import com.chaoyue.haodebar.usermanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
@Slf4j
@RestController("/userManager")
public class UserController implements UserControllerApi {

    @Autowired
    UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    @PostMapping ("/saveData")
    public UserModel SaveData() {
        log.info("xzc");
        UserModel model = new UserModel();
        model.setVersion("版本");
        model.setUserName("许智超");
        model.setEmail("xzc@qq.com");
        model.setPhone("18810440946");
        //userService.save(model);
        redisTemplate.opsForValue().set("xzctset",1);
        return model;
    }
}
