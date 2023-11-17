package com.autobiography.users.config.email;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@AllArgsConstructor
@Configuration
public class EmailConfig {

    private final EmailProperties emailProps;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(emailProps.getHost());
        mailSender.setPort(emailProps.getPort());
        mailSender.setUsername(emailProps.getUsername());
        mailSender.setPassword(emailProps.getPassword());
        mailSender.setJavaMailProperties(getProperties());

        return mailSender;
    }

    private Properties getProperties() {
        Properties props = new Properties();

       props.put("mail.smtp.auth", emailProps.isAuth());
       props.put("mail.smtp.starttls.enable", emailProps.isStarttlsEnable());
       props.put("mail.smtp.starttls.required", emailProps.isStarttlsEnable());
       props.put("mail.smtp.connection-timeout", emailProps.getConnectionTimeout());
       props.put("mail.smtp.timeout", emailProps.getTimeout());
       props.put("mail.smtp.write-timeout", emailProps.getWriteTimeout());

       return props;
    }
}
