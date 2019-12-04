package com.chaoyue.manager;

import com.chaoyue.manager.usermanage.model.UserModel;
import com.chaoyue.manager.usermanage.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
@SpringBootTest
public class MyTest {
    @Autowired
    UserService userService;

    @Test
    void Save(){
        UserModel userModel = new UserModel();
        userModel.setPhone("123");
        userService.save(userModel);
    }
}
