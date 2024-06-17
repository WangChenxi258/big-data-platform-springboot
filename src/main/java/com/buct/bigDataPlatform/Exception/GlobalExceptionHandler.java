package com.buct.bigDataPlatform.Exception;

import com.buct.bigDataPlatform.Pojo.Enums.ResultStatus;
import com.buct.bigDataPlatform.Pojo.Result;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-15 14:15
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(Exception e) {
        e.printStackTrace();
        return Result.FAIL(e.getMessage(), ResultStatus.VALIDATION_FAILED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(Exception e) {
        e.printStackTrace();
        return Result.FAIL(e.getMessage(), ResultStatus.VALIDATION_FAILED);
    }
}