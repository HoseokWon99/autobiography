package com.autobiography.users.service;

import com.autobiography.users.utils.enumeration.EmailVerifyingResults;
import com.autobiography.users.utils.exception.EmailException;
import com.autobiography.users.utils.cache.KeyValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EMailCertificationServiceImpl implements EMailCertificationService {
    private static final long TIMEOUT = Duration.ofMinutes(5).toMillis();

    private final EMailService eMailService;
    private final KeyValueRepository<String, Object> kvRepos;

    @Override
    public String sendCode(String to) {
        String key = UUID.randomUUID().toString();
        String code = createCode();

        eMailService.sendEMail(to, "자서전 인증", code);
        kvRepos.setData(key, code, TIMEOUT);

        return key;
    }

    @Override
    public int verifyCode(String key, String code) {
        String correctCode = (String) kvRepos.getData(key);

        if (correctCode == null)
            return EmailVerifyingResults.UNEXPECTED_KEY.value;

        return code.equals(correctCode) ?
                EmailVerifyingResults.OK.value :
                EmailVerifyingResults.INCORRECT_CODE.value;
    }

    private static String createCode() {

        try {
            SecureRandom rand = SecureRandom.getInstanceStrong();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i != 6; ++i)
                sb.append(rand.nextInt(10));

            return sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new EmailException(e);
        }

    }


}
