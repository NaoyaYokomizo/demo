package com.nc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("/sample")
    public String sample() {
        return "sample";
    }
    @RequestMapping("/sample2")
    public String sample2() {
        return "sample";
    }
}
