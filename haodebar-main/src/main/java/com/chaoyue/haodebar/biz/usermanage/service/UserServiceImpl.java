package com.chaoyue.haodebar.biz.usermanage.service;

import com.chaoyue.common.base.service.impl.BaseServiceImpl;
import com.chaoyue.haodebar.api.model.UserModel;
import com.chaoyue.haodebar.biz.usermanage.dao.UserDao;
import com.chaoyue.haodebar.api.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, UserModel> implements UserService {
}
