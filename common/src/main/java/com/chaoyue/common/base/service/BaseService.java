package com.chaoyue.common.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chaoyue.common.base.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
public interface BaseService<T extends BaseModel> extends IService<T> {
}
