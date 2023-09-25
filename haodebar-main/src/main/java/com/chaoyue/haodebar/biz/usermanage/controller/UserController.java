package com.chaoyue.haodebar.biz.usermanage.controller;

import com.chaoyue.common.base.Result;
import com.chaoyue.haodebar.api.model.UserModel;
import com.chaoyue.haodebar.biz.usermanage.api.UserControllerApi;
import com.chaoyue.haodebar.biz.usermanage.domain.UserReqDto;
import com.chaoyue.haodebar.biz.usermanage.model.UserElasticModel;
import com.chaoyue.haodebar.tool.ElasticSearchUtils;
import com.chaoyue.haodebar.mq.MqProducerProcessor;
import com.chaoyue.haodebar.api.service.UserService;
import com.chaoyue.haodebar.tool.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
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
    @Resource
    private ElasticSearchUtils elasticSearchUtils;
    @Resource
    private ElasticsearchRestTemplate restTemplate;

    @Resource
    private MqProducerProcessor mqProcessor;
    @Resource
    private RedissonClient redissonClient;

    @Resource
    private MailUtils mailUtils;

    @Override
    @PostMapping ("/saveData")
    public Result<UserModel> saveData() {
        RLock lock = redissonClient.getLock("aaa");
        lock.tryLock();

        if(log.isInfoEnabled()){
            log.info("log info");
        }

        log.error("log error");
        mailUtils.sendSimpleMail("1352425876@qq.com","test","你是好人");
        UserModel model = new UserModel();
        model.setVersion("版本");
        model.setUserName("许智超");
        model.setEmail("xzc@qq.com");
        model.setPhone("18810440946");
        redisTemplate.opsForValue().set("xzctset",1);
        try {
            elasticSearchUtils.creatIndex("test1");
        }catch (Exception e){
            e.printStackTrace();
        }
        userService.save(model);
        UserElasticModel userElasticModel =new UserElasticModel();
        BeanUtils.copyProperties(model,userElasticModel);
        restTemplate.save(userElasticModel);

        mqProcessor.send("我来了");
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
