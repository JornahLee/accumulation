package com.jornah.springmvcdemo.controller;


import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String getMsg() throws IOException, TemplateException {
        return "index";
    }
}
