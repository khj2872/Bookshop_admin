package com.kobobook.www.admin.exception;

public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException() { }

    public NotEnoughStockException(String message) {
        super(message);
    }

}