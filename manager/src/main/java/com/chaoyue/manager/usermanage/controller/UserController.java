package com.chaoyue.manager.usermanage.controller;

import com.chaoyue.manager.usermanage.api.UserControllerApi;
import com.chaoyue.manager.usermanage.model.UserModel;
import com.chaoyue.manager.usermanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    @PostMapping ("/saveData")
    public void SaveData() {
        log.info("xzc");
        UserModel model = new UserModel();
        model.setVersion("版本");
        userService.save(model);
    }
}
