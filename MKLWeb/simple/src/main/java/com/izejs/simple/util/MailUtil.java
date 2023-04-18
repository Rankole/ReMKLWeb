package com.izejs.simple.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sendMailUser;

    private String toMailUser;

    private String subject;

    private String Content;

    public String getSendMailUser() {
        return sendMailUser;
    }

    public void setSendMailUser(String sendMailUser) {
        this.sendMailUser = sendMailUser;
    }

    public String getToMailUser() {
        return toMailUser;
    }

    public void setToMailUser(String toMailUser) {
        this.toMailUser = toMailUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendMailUser);
        message.setTo(this.toMailUser);
        message.setSubject(this.subject);
        message.setText(this.Content);
        mailSender.send(message);
    }

}
