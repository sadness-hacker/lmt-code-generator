package com.lmt.code.exception;

import lombok.Getter;

/**
 * TODO add @description for this class
 *
 * @author bazhandao
 * @date 2018/11/2 14:39
 * @since JDK1.8
 */
@Getter
public class BasicException extends RuntimeException {

    private String code;

    private String msg;

    public BasicException() {}

    public BasicException(String code, String msg) {
        super("code=" + code + ",msg=" + msg);
    }

    public BasicException(String code, String msg, Exception e) {
        super("code=" + code + ",msg=" + msg, e);
    }

    public BasicException(Exception e) {
        super("code=9999,msg=系统异常", e);
    }

}
