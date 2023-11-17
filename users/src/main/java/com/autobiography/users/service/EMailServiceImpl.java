package com.autobiography.users.service;

import com.autobiography.users.utils.exception.EmailException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EMailServiceImpl implements EMailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEMail(String to, String subject, String text) {

        try {
            MimeMessage msg = createMessage(to, subject, text);
            mailSender.send(msg);
        }
        catch (MessagingException | MailException e) {
            throw new EmailException(e);
        }

    }

    private MimeMessage createMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();

        msg.setRecipients(Message.RecipientType.TO, to);
        msg.setSubject(subject, "utf-8");
        msg.setText(text, "utf-8");

        return msg;
    }
}
