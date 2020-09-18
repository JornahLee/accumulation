package com.jornah.springmvcdemo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class MyController {
    @RequestMapping("/msg")
    public Instant getMsg(){
        return Instant.now();
    }
}
