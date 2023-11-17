package com.autobiography.users.utils.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailException extends RuntimeException {

    public EmailException(Exception e) {
        super(e);
    }

}
