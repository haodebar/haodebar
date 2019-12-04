package com.chaoyue.common.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Date:2019/12/4
 * Create by:xuzhichao
 * Describe:
 */
@Data
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 4322215503276003340L;

    @TableId(type = IdType.AUTO)
    private Long id; //数据库主键
    private Long createUser;//创建人id
    private String createName;//创建人姓名
    private Date createTime;//创建时间
    private Long modifyUser;//修改人id
    private String modifyName;//修改人姓名
    private Date modifyTime;//修改时间
    private String version;//数据版本号
    private Long enable;//有效标志
}
