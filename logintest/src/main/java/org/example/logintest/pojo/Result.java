package org.example.logintest.pojo;

import java.util.Map;

public class Result<T> {
    private static final Map <String, Object> map = null;
    private Integer code;
    private String message;
    private T data;
    public Result(){

    }
    public Result(Integer i, String message, T data) {
        this.code = i;
        this.message = message;
        this.data = data;
    }

    public static <E> Result<E> success(E data){
        return new Result<>(200,"success",data);
    }
    public static <E> Result<E> success(){
        return new Result<>(200,"success",null);
    }
    public static <E> Result<E> error(String message){
        return new Result<>(500,message,null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
