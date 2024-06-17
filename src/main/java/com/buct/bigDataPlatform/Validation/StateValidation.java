package com.buct.bigDataPlatform.Validation;

import com.buct.bigDataPlatform.Anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 16:45
 */
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     * @param s:                          将来要校验的数据
     * @param constraintValidatorContext:
     * @Description: isValid
     * @Author: CarLor
     * @Date: 16:46 2024/6/12 0012
     * @Param:
     * @return: boolean
     **/
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (s == null) {
            return false;
        }
        if (s.equals("已发布") || s.equals("草稿")) {
            return true;
        }
        return false;
    }
}