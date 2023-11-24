package com.demos.utils;

import java.io.Serializable;

//Json格式的数据进行响应
public class JsonResult<E>  implements Serializable {
    private Integer state;//状态码
    private String message;//描述信息
    private E data;//数据

    public JsonResult() {
    }
    public JsonResult(Integer state) {
        this.state = state;
    }
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
