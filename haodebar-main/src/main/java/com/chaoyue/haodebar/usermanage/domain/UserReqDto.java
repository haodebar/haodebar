package com.chaoyue.haodebar.usermanage.domain;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2022/9/9 11:39
 * @version: version 1.0
 * @dec: 请求参数
 */
@Data
public class UserReqDto {
    @NonNull
    private Long userId;
}
