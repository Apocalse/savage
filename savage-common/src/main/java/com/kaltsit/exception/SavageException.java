package com.kaltsit.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SavageException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public SavageException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public SavageException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public SavageException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public SavageException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
