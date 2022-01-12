//package com.aneeque.backendservice.email;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.MailAuthenticationException;
//import org.springframework.mail.MailParseException;
//import org.springframework.mail.MailSendException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.File;
//
///**
// * Created by Okala III on 04/03/2021.
// */
//@Slf4j
//@Service
//public class EmailServiceImpl {
//
//    @Autowired
//    private JavaMailSender emailSender;
//
//    /**
//     *
//     * @param to example@mail.com
//     * @param subject
//     * @param content
//     * @return 1 if successfull and -1 if t'was unsuccessful
//     */
//    public Integer sendSimpleMessage(String to, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        try {
//            message.setFrom("noreply@okalaIII.com");
//            message.setTo(to);
//            message.setSubject(subject);
//            message.setText(content);
//            emailSender.send(message);
//        }
//        catch (Exception exception) {
//            if (exception instanceof MailParseException) {
//                log.error("failure when parsing the message");
//            }
//            if (exception instanceof MailAuthenticationException) {
//                log.error("authentication failure");
//            }
//            if (exception instanceof MailSendException) {
//                log.error("failure when sending the message");
//            } else {
//                log.error(exception.getLocalizedMessage());
//            }
//            return -1;
//        }
//        log.info("sending mail...");
//        return 1;
//    }
//
//    /**
//     *
//     * @param to
//     * @param subject
//     * @param text
//     * @param pathToAttachment
//     * @throws MessagingException
//     */
//    public void sendMessageWithAttachment(
//            String to, String subject, String text, String pathToAttachment) {
//
//        MimeMessage message = emailSender.createMimeMessage();
//
//        MimeMessageHelper helper = null;
//        try {
//            helper = new MimeMessageHelper(message, true);
//            helper.setFrom("noreply@okalaIII.com");
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text);
//
//            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
//            helper.addAttachment("Invoice", file);
//
//        } catch (MessagingException e) {
//            log.error(e.getMessage(),e);
//        }
//
//        emailSender.send(message);
//        // ...
//    }
//
//    @Bean
//    public SimpleMailMessage templateSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText(
//                "This is the test email template for your email:\n%s\n");
//        return message;
//    }
//
//}
