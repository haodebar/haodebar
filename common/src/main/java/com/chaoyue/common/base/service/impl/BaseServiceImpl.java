package com.chaoyue.common.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chaoyue.common.base.mapper.BaseDao;
import com.chaoyue.common.base.model.BaseModel;
import com.chaoyue.common.base.service.BaseService;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
public class BaseServiceImpl<M extends BaseDao<T>,T extends BaseModel> extends ServiceImpl<M,T>  implements BaseService<T> {
}
