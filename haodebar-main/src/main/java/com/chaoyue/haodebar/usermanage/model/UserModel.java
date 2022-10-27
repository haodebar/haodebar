package com.chaoyue.haodebar.usermanage.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chaoyue.common.annotation.sensitive.Sensitive;
import com.chaoyue.common.annotation.sensitive.SensitiveTypeEnum;
import com.chaoyue.common.base.model.BaseModel;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao
 * @date: 2022/10/8 16:38
 * @version: version 1.0
 * @dec: 描述信息
 */
@Data
@TableName("manage_user")
public class UserModel extends BaseModel {
    private static final long serialVersionUID = -6157193063523644981L;

    @Sensitive(type = SensitiveTypeEnum.NAME)
    private String userName;
    @Sensitive(type = SensitiveTypeEnum.NAME)
    private String phone;
    @Sensitive(type = SensitiveTypeEnum.NAME)
    private String email;

}
