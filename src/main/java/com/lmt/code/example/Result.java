package com.lmt.code.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description for this class
 *
 * @author bazhandao
 * @date 2018/11/8 16:35
 * @since JDK1.8
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Result<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public void setError(int code, String msg) {

    }

}
