package io.github.qaqchat.logindemo.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
public class EmailSender {
    // SMTP 服务器信息
    @Value("${qaqchat.email-sender.host}")
    private String host;
    @Value("${qaqchat.email-sender.port}")
    private String port;
    @Value("${qaqchat.email-sender.from}")
    private String from;
    @Value("${qaqchat.email-sender.password}")
    private String password;

    public void sendVerificationCode(String to, String purpose, String code) {
        String subject = "QAQchat Verification Code";
        String body = "Your " + purpose + "verification code is " + code + ".";
        sendEmail(to, subject, body);
    }

    private void sendEmail(String to, String subject, String body) {
        // 设置邮件服务器属性
        Properties properties = getProperties();

        // 创建会话对象
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // 创建邮件内容
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // 发送邮件
            Transport.send(message);
            log.info("Email sent successfully.");

        } catch (MessagingException e) {
            log.error("Failed to send email", e);
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        // properties.put("mail.debug", "true"); // Enable debugging
        return properties;
    }
}
