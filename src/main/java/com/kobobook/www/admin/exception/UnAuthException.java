package com.kobobook.www.admin.exception;

import org.springframework.security.core.AuthenticationException;

public class UnAuthException extends AuthenticationException {
    public UnAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public UnAuthException(String msg) {
        super(msg);
    }
}
