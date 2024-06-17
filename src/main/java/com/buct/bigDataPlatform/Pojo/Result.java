package com.buct.bigDataPlatform.Pojo;

import com.buct.bigDataPlatform.Pojo.Enums.ResultStatus;
import lombok.Data;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-15 14:39
 */
@Data
public class Result<T> {
    private Integer code;//业务状态码 0-成功 1-失败
    private String message;//提示信息
    private T data;//响应数据

    public static <T> Result<T> OK(T data) {
        return packageObject(data, ResultStatus.SUCCESS);
    }

    public static Result OK() {
        return packageObject(null, ResultStatus.SUCCESS);
    }

    public static <T> Result<T> FAIL(T data) {
        return packageObject(data, ResultStatus.FAILURE);
    }

    public static Result FAIL(ResultStatus resultStatus) {
        return packageObject(null, resultStatus);
    }

    public static <T> Result<T> FAIL(T data, ResultStatus resultStatus) {
        return packageObject(data,resultStatus);
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"message\":\"" + message + '\"' +
                ", \"data\":" + (data == null) +
                '}';
    }

    private static <T> Result<T> packageObject(T data, ResultStatus resultStatus) {
        Result<T> result = new Result<>();
        result.setCode(resultStatus.getCode());
        result.setMessage(resultStatus.getMessage());
        result.setData(data);
        return result;
    }

}