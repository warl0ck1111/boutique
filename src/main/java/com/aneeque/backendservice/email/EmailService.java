//package com.aneeque.backendservice.email;
//
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Properties;
//import java.util.UUID;
//import java.util.logging.Logger;
//
///**
// * Created by OKala on 04/08/2018.
// */
//
//@Slf4j
//@Data
//@Service
//@EnableAsync
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${spring.mail.host}")
//    private String smtpHost;
//
//    @Value("${spring.mail.port}")
//    private String smtpPort;
//
//    @Value("${spring.mail.username}")
//    private String sender;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    @Value("${senderEmail}")
//    private String senderEmail;
//
//    @Value("${com.oasis.image.directory}")
//    private String storageDirectoryPath;
//
//    private static final Logger logger = Logger.getLogger(EmailService.class.getName());
//
//    @Async
//    public void sendEmail(String subject, String content, String to, ByteArrayOutputStream outputStream) {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", smtpHost);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.port", smtpPort);
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getDefaultInstance(properties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(sender, password);
//                    }
//                });
//
//
//        try {
////            MimeBodyPart textBodyPart = new MimeBodyPart();
////            textBodyPart.setContent(setTemplate2(content), "text/html");
////            byte[] bytes = outputStream.toByteArray();
////
////            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
////            MimeBodyPart pdfBodyPart = new MimeBodyPart();
////            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
////            pdfBodyPart.setFileName("STAMP DUTY NOTIFICATION");
////            MimeMultipart mimeMultipart = new MimeMultipart();
////            mimeMultipart.addBodyPart(textBodyPart);
////            mimeMultipart.addBodyPart(pdfBodyPart);
////            InternetAddress iaSender = new InternetAddress(senderEmail);
////            InternetAddress iaRecipient = new InternetAddress(to);
////
////            MimeMessage mimeMessage = new MimeMessage(session);
////            mimeMessage.setFrom(new InternetAddress(senderEmail));
////            mimeMessage.setSender(iaSender);
////            mimeMessage.setSubject(subject);
////            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
////            mimeMessage.setContent(mimeMultipart);
////            Transport.send(mimeMessage);
//
////            bodyPart.setContent(message, "text/html; charset=ISO-8859-1");
//
//            log.info("sent from " + senderEmail + ", to " + to + "; server = " + smtpHost + ", port = " + smtpPort);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            if (null != outputStream) {
//                try {
//                    outputStream.close();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Async
//    public void sendEmail(String subject, String content, String to) throws MessagingException {
//        buildEmailMessage(subject, to, setTemplate(content));
//    }
//
//    private void buildEmailMessage(String subject, String to, String s) throws MessagingException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
//        mimeMessage.setContent(s, "text/html");
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setFrom(senderEmail);
//        mailSender.send(mimeMessage);
//    }
//
//    String generateContentId(String prefix) {
//        return String.format("%s-%s", prefix, UUID.randomUUID());
//    }
//
//    @Async
//    public void sendEmailWithImage(String subject, String bodyContent, String to) throws MessagingException, IOException {
//
//        String presidentialSealCID = this.generateContentId("presidentialSeal");
//
//        MimeMultipart content = new MimeMultipart("related");
//
//        MimeBodyPart htmlPart = new MimeBodyPart();
//        htmlPart.setText(setTemplate1(bodyContent, presidentialSealCID), "US-ASCII", "html");
//        content.addBodyPart(htmlPart);
//
//        //Adding the Image/Attachement As Another Body Part
//        MimeBodyPart imagePart = new MimeBodyPart();
//        imagePart.attachFile("src/main/resources/static/images/seal-of-the-president.png");
//        imagePart.attachFile(storageDirectoryPath + File.separator + "seal-of-the-president.png");
//
//        imagePart.setContentID("<" + presidentialSealCID + ">");
//        imagePart.setDisposition(MimeBodyPart.INLINE);
//        content.addBodyPart(imagePart);
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
//        mimeMessage.setContent(content, "text/html");
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setFrom(senderEmail);
//        mailSender.send(mimeMessage);
//    }
//
//    @Async
//    public void sendEmailWithImageAndAttachment(String subject, String bodyContent, String to, ByteArrayOutputStream baos, String fileName) throws MessagingException, IOException {
//
//        String presidentialSealCID = this.generateContentId("presidentialSeal");
//
////        MimeMultipart content = new MimeMultipart("related");
//
////        MimeBodyPart htmlPart = new MimeBodyPart();
////        htmlPart.setText(setTemplate1(bodyContent, presidentialSealCID), "US-ASCII", "html");
////        content.addBodyPart(htmlPart);
//
//        //Adding the inline Image As Another Body Part
////        MimeBodyPart imagePart = new MimeBodyPart();
////        imagePart.attachFile("src/main/resources/static/images/seal-of-the-president.png");
////        imagePart.setContentID("<" + presidentialSealCID + ">");
////        imagePart.setDisposition(MimeBodyPart.INLINE);
////        content.addBodyPart(imagePart);
//
//        //add pdf attachment
////        MimeBodyPart pdfPart = new MimeBodyPart();
////        DataSource dataSource = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
////        pdfPart.setHeader("Content-Transfer-Encoding", "base64");
////        pdfPart.setDataHandler(new DataHandler(dataSource));
////        pdfPart.setFileName(fileName);
////        pdfPart.setDisposition(MimeBodyPart.ATTACHMENT);
////        content.addBodyPart(pdfPart);
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
//        helper.setText(setTemplate1(bodyContent, presidentialSealCID), true);
//        helper.addAttachment(fileName, new ByteArrayResource(baos.toByteArray()), "application/pdf");
////        helper.addInline(  presidentialSealCID  , new File("src/main/resources/static/images/seal-of-the-president.png"));
//        helper.addInline(presidentialSealCID, new File(storageDirectoryPath + File.separator + "seal-of-the-president.png"));
//        //mimeMessage.setContent(content, "text/html");
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setFrom(senderEmail);
//
//        mailSender.send(mimeMessage);
//    }
//
//
//    private String setTemplate(String content) {
//        return "<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <title>Aneeque</title>\n" +
//                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
//                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
//                "    <link href=\"https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500;600;700;800;900&display=swap\"\n" +
//                "        rel=\"stylesheet\">\n" +
//                "</head>\n" +
//                "<body style=\"padding: 0; margin: 0; box-sizing: border-box;\">\n" +
//                "    <section style=\"font-family: Jost, sans-serif; height: 100vh; background-color: #E5E5E5; display: flex; justify-content: center; align-items: center;\">\n" +
//                "        <div style=\"width: 45%; height: 50vh; background-color: #fff; padding: 50px 20px; display: flex; flex-direction: column; justify-content: center; align-items: center;\">\n" +
//                "            <img style=\"width: 120px; height: 80px; object-fit: contain;\" src=\"https://drive.google.com/thumbnail?id=1A6DgglbfKd00vp2Xcv_7HHTAU82ZwJ9A\" alt=\"aneeque\">\n" +
//                "            <h3 style=\"font-size: 18px; text-transform: uppercase; font-weight: 600;\">\n" +
//                "                activate your account\n" +
//                "            </h3>\n" +
//                "            <p style=\"width: 60%; font-size: 15px; margin: 5px auto; text-align: center; opacity: .85;\">\n" +
//                "                Hey there! Click activate button below to verify \n" +
//                "                your account and get started on Aneeque!\n" +
//                "            </p>\n" +
//                "            <a href=\"https://www.google.com\" target=\"_blank\" rel=\"noreferrer\" style=\"font-size: 14px; font-weight: 500; text-decoration: none; font-family: Jost, sans-serif; text-transform: uppercase; text-align: center; cursor: pointer; margin: 40px auto 10px auto; background-color: #2D2D2D; border: none; border-radius: 5px; color: #fff; padding: 15px; display: block; width: 55%;\">\n" +
//                "                activate account\n" +
//                "            </a>\n" +
//                "    \n" +
//                "            <p style=\"font-size: 15px;\">\n" +
//                "                If you did not register using this email, please ignore this email.\n" +
//                "            </p>\n" +
//                "        </div>\n" +
//                "    </section>\n" +
//                "</body>\n" +
//                "</html>\n";
//    }
//
//
//    private String setTemplate1(String content, String cid) {
//        return "<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <title>Aneeque</title>\n" +
//                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
//                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
//                "    <link href=\"https://fonts.googleapis.com/css2?family=Jost:wght@300;400;500;600;700;800;900&display=swap\"\n" +
//                "        rel=\"stylesheet\">\n" +
//                "</head>\n" +
//                "<body style=\"padding: 0; margin: 0; box-sizing: border-box;\">\n" +
//                "    <section style=\"font-family: Jost, sans-serif; height: 100vh; background-color: #E5E5E5; display: flex; justify-content: center; align-items: center;\">\n" +
//                "        <div style=\"width: 45%; height: 50vh; background-color: #fff; padding: 50px 20px; display: flex; flex-direction: column; justify-content: center; align-items: center;\">\n" +
//                "            <img style=\"width: 120px; height: 80px; object-fit: contain;\" src=\"https://drive.google.com/thumbnail?id=1A6DgglbfKd00vp2Xcv_7HHTAU82ZwJ9A\" alt=\"aneeque\">\n" +
//                "            <h3 style=\"font-size: 18px; text-transform: uppercase; font-weight: 600;\">\n" +
//                "                activate your account\n" +
//                "            </h3>\n" +
//                "            <p style=\"width: 60%; font-size: 15px; margin: 5px auto; text-align: center; opacity: .85;\">\n" +
//                "                Hey there! Click activate button below to verify \n" +
//                "                your account and get started on Aneeque!\n" +
//                "            </p>\n" +
//                "            <a href=\"https://www.google.com\" target=\"_blank\" rel=\"noreferrer\" style=\"font-size: 14px; font-weight: 500; text-decoration: none; font-family: Jost, sans-serif; text-transform: uppercase; text-align: center; cursor: pointer; margin: 40px auto 10px auto; background-color: #2D2D2D; border: none; border-radius: 5px; color: #fff; padding: 15px; display: block; width: 55%;\">\n" +
//                "                activate account\n" +
//                "            </a>\n" +
//                "    \n" +
//                "            <p style=\"font-size: 15px;\">\n" +
//                "                If you did not register using this email, please ignore this email.\n" +
//                "            </p>\n" +
//                "        </div>\n" +
//                "    </section>\n" +
//                "</body>\n" +
//                "</html>\n";
//    }
//
//
//    public String getMyString() {
//        String base64String = "";
//
//        try {
//            base64String = new String(Files.readAllBytes(Paths.get("src/main/resources/static/imageString.txt")));
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//        return base64String;
//    }
//
//}
//
