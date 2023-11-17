package com.autobiography.users.service;

public interface EMailCertificationService {
    String sendCode(String to);
    int verifyCode(String key, String code);
}
