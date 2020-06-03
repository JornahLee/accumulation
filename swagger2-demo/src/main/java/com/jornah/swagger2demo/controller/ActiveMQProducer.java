package com.jornah.swagger2demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(description = "生产者进程API接口")
@RestController
@RequestMapping("/producer")
public class ActiveMQProducer {


    @ApiOperation(value = "1连接线路",
            notes = "1111")
    @PostMapping(value = { "/channels/{id:\\-1|\\d+}/connect12312312","asdasdasdasd"},
            produces = APPLICATION_JSON_VALUE, headers = "api-version=v1.0")
    public String sendText(@PathVariable String id) {
        System.out.println("--licg---     fucntion1 :     -----");

        return "SUCESS";
    }

    @ApiOperation(value = "123连接线路",
            notes = "123",
            authorizations = @Authorization("Bearer"))
    @PostMapping(value = "/channels/{id:\\-1|\\d+}/connect", produces = APPLICATION_JSON_VALUE, headers = {"api-version=v1.0"})
    public String queryWordCount(@PathVariable String id) {
        System.out.println("--licg---     function2 :  -----");
        return "SUCESS";
    }
}