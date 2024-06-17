package com.buct.bigDataPlatform.Anno;

import com.buct.bigDataPlatform.Validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 16:41
 */
@Documented//元注解
@Constraint(
        validatedBy = {StateValidation.class}
)
@Target({ElementType.FIELD})//元注解
@Retention(RetentionPolicy.RUNTIME)//元注解
public @interface State {
    //校验失败的提示信息
    String message() default "state参数的值只能是 已发布 或 草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载 获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
