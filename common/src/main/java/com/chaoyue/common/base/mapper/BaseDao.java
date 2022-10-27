package com.chaoyue.common.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chaoyue.common.base.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
public interface BaseDao<T extends BaseModel> extends BaseMapper<T> {
}
