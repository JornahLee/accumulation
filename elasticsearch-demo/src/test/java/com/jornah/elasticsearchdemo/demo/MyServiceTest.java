package com.jornah.elasticsearchdemo.demo;

import com.jornah.elasticsearchdemo.dao.PersonRepository;
// import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyServiceTest {
    @Autowired
    private PersonRepository repository;

    // @Test
    public void test1() {
        MyService myService = new MyService(repository);
        myService.doWork();

    }

}