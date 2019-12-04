package com.chaoyue.common.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chaoyue.common.base.mapper.BaseMapper;
import com.chaoyue.common.base.model.BaseModel;
import com.chaoyue.common.base.service.BaseService;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T extends BaseModel> extends ServiceImpl<M,T>  implements BaseService<T> {
}
