package com.chaoyue.haodebar.usermanage.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chaoyue.common.base.model.BaseModel;
import lombok.Data;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
@Data
@TableName("manage_user")
public class UserModel extends BaseModel {
    private static final long serialVersionUID = -6157193063523644981L;

    private String userName;
    private String phone;
    private String email;

}
