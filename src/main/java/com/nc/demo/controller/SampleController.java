package com.nc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("/sampleA")
    public String sampleA() {
        return "sampleA";
    }

    @RequestMapping("/sampleU")
    public String sampleU() {
        return "sampleU";
    }
}
