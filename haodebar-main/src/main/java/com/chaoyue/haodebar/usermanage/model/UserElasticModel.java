package com.chaoyue.haodebar.usermanage.model;

import com.chaoyue.common.annotation.sensitive.Sensitive;
import com.chaoyue.common.annotation.sensitive.SensitiveTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/3/7 16:46
 * @version: version 1.0
 * @dec: 描述信息
 */
@Document(indexName = "test")
@Data
public class UserElasticModel {
    @Id
    private Long id;
    @Field
    private String userName;
    @Field
    private String phone;
    @Field
    private String email;
}
