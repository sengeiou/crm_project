package com.javasm.commons.exception;

import com.javasm.commons.entity.E;

/**
 * @author:
 * @className:MvcException
 * @description:
 * @date:2021/12/28 20:01
 * @version:0.1
 * @since:1.8
 */
public class MvcException extends RuntimeException {

    private E e;


    public MvcException(E e) {
        this.e = e;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
}
