package com.jornah.springmvcdemo.util;


import com.jornah.springmvcdemo.utils.EmailDao;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailDao emailDao;

    @Test
    public void testSendEmail(){
        String from="li@223.com";
        String to="614042560@qq.com";
        String subject="test title";
        String content="test content";
        emailDao.sendEmail(from,to,subject,content);

    }
    @Test
    public void testSendEmailByHttp() throws UnirestException {
        String from="li@223.com";
        // String to="licong@shoplex.com";
        String to="614042560@qq.com";
        String subject="test title";
        String content="test content";
        emailDao.sendEmailByHttp(from,to,subject,content);

    }
}
