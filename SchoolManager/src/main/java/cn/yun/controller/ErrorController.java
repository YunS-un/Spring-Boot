package cn.yun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/4xxPage")
    public String error4xx(){
        return "error/4xx";
    }

    @RequestMapping("/5xxPage")
    public String error500(){
        return "error/5xx";
    }

}
