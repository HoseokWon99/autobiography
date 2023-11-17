package com.autobiography.users.utils.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(Exception e) {
        super(e);
    }

}
