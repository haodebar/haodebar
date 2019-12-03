package com.chaoyue.common.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 4322215503276003340L;

    /**
     * 数据库主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 创建人id
     */
    private Long createUser;
    /**
     *创建人姓名
     */
    private String createName;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *修改人id
     */
    private Long modifyUser;
    /**
     *修改人姓名
     */
    private String modifyName;
    /**
     *修改时间
     */
    private Date modifyTime;
    /**
     *数据版本号
     */
    private String version;
    /**
     *有效标志
     */
    @TableLogic(value = "1",delval = "0")
    private Long enable;
}
