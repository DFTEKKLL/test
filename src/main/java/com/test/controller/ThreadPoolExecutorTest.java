package com.test.controller;

import com.test.service.ThreadPoolExecutorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping
public class ThreadPoolExecutorTest {

    @Resource
    ThreadPoolExecutorService threadPoolExecutorService;

    @RequestMapping("/poolTest")
    @ResponseBody
    public String poolTest(@RequestParam(required = false) String echostr) {
        threadPoolExecutorService.exService();
        return echostr;
    }
}
