package com.jornah.elasticsearch.service;

import com.jornah.elasticsearch.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {

    @Autowired
    private TestService testService;
    @Test
    public void test1(){
        // Article article = testService.newArticle("123 title", "123 text");
        testService.save("123 title", "123 text");
        Object title = testService.search("t", PageRequest.of(0, 10));
        System.out.println("title = " + title);
    }
}