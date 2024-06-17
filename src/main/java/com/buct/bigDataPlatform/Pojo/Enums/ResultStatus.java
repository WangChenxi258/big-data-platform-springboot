package com.buct.bigDataPlatform.Pojo.Enums;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-15 14:43
 */
public enum ResultStatus {
    /**
     * 成功
     */
    SUCCESS(0, "请求成功"),
    /**
     * 失败
     */
    FAILURE(1, "请求失败"),
    /**
     * 失败
     */
    EXCEPTION_EXCEPTION(1001, "业务异常"),
    /**
     * 认证授权失败
     */
    NO_AUTHORIZATION(401, "认证授权失败"),
    /**
     * 参数校验失败
     */
    VALIDATION_FAILED(402, "参数校验失败"),
    ;

    private int code;
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private static HashMap<Integer, ResultStatus> map = Maps.newHashMap();

    static {
        for (ResultStatus d : ResultStatus.values()) {
            map.put(d.code, d);
        }
    }

    public static ResultStatus parse(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        return null;
    }

}
