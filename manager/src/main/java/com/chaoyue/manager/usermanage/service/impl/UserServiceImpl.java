package com.chaoyue.manager.usermanage.service.impl;

import com.chaoyue.common.base.service.BaseService;
import com.chaoyue.common.base.service.impl.BaseServiceImpl;
import com.chaoyue.manager.usermanage.dao.UserDao;
import com.chaoyue.manager.usermanage.model.UserModel;
import org.springframework.stereotype.Service;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, UserModel> implements BaseService<UserModel> {
}
