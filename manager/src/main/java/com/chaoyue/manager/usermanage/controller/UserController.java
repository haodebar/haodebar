package com.chaoyue.manager.usermanage.controller;

import com.chaoyue.manager.usermanage.api.UserControllerApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
@RestController("userManager")
public class UserController implements UserControllerApi {

    @Override
    @PostMapping("saveData")
    public void SaveData() {
        System.out.println("我进入方法");
    }
}
