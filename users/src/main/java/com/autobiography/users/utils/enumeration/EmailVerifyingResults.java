package com.autobiography.users.utils.enumeration;

public enum EmailVerifyingResults {
    OK(0),
    INCORRECT_CODE(1),
    UNEXPECTED_KEY(2);

    public final int value;

    EmailVerifyingResults(int value) {
        this.value = value;
    }
}
