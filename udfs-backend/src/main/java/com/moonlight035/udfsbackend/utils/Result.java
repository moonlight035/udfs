package com.moonlight035.udfsbackend.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int status;
    private Object data;
    private String error;

    private Result(){}

    public static Result success(Object data){
        Result result = new Result();
        result.data = data;
        result.status = 200;
        return result;
    }

    public static Result fail(int status,String error){
        Result result = new Result();
        result.error = error;
        result.status = status;
        return result;
    }
}
