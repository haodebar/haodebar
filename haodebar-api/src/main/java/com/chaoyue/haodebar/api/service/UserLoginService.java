package com.chaoyue.haodebar.api.service;

import com.chaoyue.common.base.Result;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/25 23:55
 * @version: version 1.0
 * @dec: 描述信息
 */
public interface UserLoginService {

    /**
     * 手机号登陆
     * @return
     */
    Result loginByPhone();

    /**
     * 邮件登陆
     * @return
     */
    Result loginByEmail();

}
