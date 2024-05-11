package com.chaoyue.common.annotation.access;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface IpAccessPermission {
    String  whiteIpsKey() default "";

    String  blackIpsKey() default "";
}
