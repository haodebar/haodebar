package com.chaoyue.common.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chaoyue.common.base.model.BaseModel;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
public interface BaseDao<T extends BaseModel> extends BaseMapper<T> {
}
