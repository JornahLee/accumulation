package com.jornah.springmvcdemo.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Component
public class EmailDao {

    @Autowired
    private JavaMailSender emailSender;
    private RestTemplate restTemplate = new RestTemplate();
    private String senderName = "isMe";

    public void sendEmail(String from, String to, String subject, String content) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
            helper.setFrom(from, senderName);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println("send failed");
        }
    }

    /**
     * mailgun 使用http方式发送邮件
     * @throws UnirestException
     */
    public void sendEmailByHttp(String from, String to, String subject, String content) throws UnirestException {
        String newFrom = "LEE" + " <" +"woshi@qq.com"+">";
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + "mg.skynetsafe.com" + "/messages")
                .basicAuth("api", "**")
                .field("from", newFrom)
                .field("to", to)
                .field("subject", subject)
                .field("text", content)
                .asJson();
        System.out.println(request.getBody());
    }

}

