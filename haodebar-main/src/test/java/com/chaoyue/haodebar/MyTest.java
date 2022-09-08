package com.chaoyue.haodebar;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
@SpringBootTest
public class MyTest {
    public static void main(String[] args) {

    }
   /* @Autowired
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
        redisTemplate.opsForValue().set("许智超","xzc");

        System.out.println();
        //redisTemplate.opsForValue().set("sdf1",map);
    }*/
}
