package com.chaoyue.haodebar.biz.login.controller;

import com.chaoyue.common.base.Result;
import com.chaoyue.haodebar.biz.login.api.UserLoginControllerApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/8/18 16:54
 * @version: version 1.0
 * @dec: 描述信息
 */
@RestController("/login")
public class UserLoginController implements UserLoginControllerApi {
    @Override
    @PostMapping("/login")
    public Result login() {
        return null;
    }

    @Override
    @PostMapping("/logout")
    public Result logout() {
        return null;
    }

    @Override
    @PostMapping("/loginEmail")
    public Result loginEmail() {
        return null;
    }

    @Override
    @PostMapping("/loginPhone")
    public Result loginPhone() {
        return null;
    }

}
