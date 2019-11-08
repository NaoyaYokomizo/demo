package com.nc.demo.controller;

import java.security.Principal;

import com.nc.demo.config.WebUserDetails;
import com.nc.demo.db.Users;
import com.nc.demo.db.UsersMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UsersMapper usersMapper;
    
    @RequestMapping("/")
    public String index(Principal principal) {
        String redirecturl = "";
        Authentication auth = (Authentication)principal;
        WebUserDetails userDetails = (WebUserDetails)auth.getPrincipal();
        Users users = usersMapper.selectByPrimaryKey(userDetails.getUsername());
        switch (users.getAuthority()) {
            case 0:
                redirecturl = "/sampleA";
                break;
            default:
                redirecturl = "/sampleU";
        }
        return "redirect:" + redirecturl;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/login-error";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}