package com.chaoyue.manager;

import com.chaoyue.manager.usermanage.model.UserModel;
import com.chaoyue.manager.usermanage.service.UserService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
@SpringBootTest
public class MyTest {
    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void Save(){
        UserModel userModel = new UserModel();
        userModel.setPhone("123");
        userModel.setEmail("1234@QQ.COM");
        userModel.setUserName("xuzhichao");
        userModel.setCreateName("1123");
        userModel.setCreateTime(new Date());
        userModel.setCreateUser(1l);

        userModel.setModifyName("11");
        userModel.setModifyTime(new Date());
        userModel.setModifyUser(3l);
        userModel.setVersion("1256666");
        userModel.setEnable(1l);
        userService.save(userModel);
    }

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("sdf","xzc");
        Map map =new HashMap();
        map.put("真的1","整的");
        map.put("真的2","2");
        map.put("真的3",map);
        try {
            redisTemplate.opsForSet().add("sdf1",map);
        }catch (Exception e){
            throw e;
        }


        System.out.println();
        //redisTemplate.opsForValue().set("sdf1",map);
    }
}
