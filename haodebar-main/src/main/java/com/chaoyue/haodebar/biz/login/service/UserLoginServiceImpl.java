package com.chaoyue.haodebar.biz.login.service;

import com.chaoyue.common.base.Result;
import com.chaoyue.haodebar.api.service.UserLoginService;
import com.chaoyue.haodebar.api.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/26 0:01
 * @version: version 1.0
 * @dec: 用户登陆
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private UserService userService;

    @Override
    public Result loginByPhone() {
        return null;
    }

    @Override
    public Result loginByEmail() {
        return null;
    }
}
