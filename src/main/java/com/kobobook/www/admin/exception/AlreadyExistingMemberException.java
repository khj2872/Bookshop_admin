package com.kobobook.www.admin.exception;

public class AlreadyExistingMemberException extends RuntimeException {
    public AlreadyExistingMemberException() { }

    public AlreadyExistingMemberException(String message) {
        super(message);
    }
}
