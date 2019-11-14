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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/fullcalendar-events")
    @ResponseBody
    public String eventsjson(Model model) {
        String s = "";
        s = s + "[                                            ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"All Day Event\",            ";
        s = s + "    \"start\": \"2019-11-01\"                ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Long Event\",               ";
        s = s + "    \"start\": \"2019-11-07\",               ";
        s = s + "    \"end\": \"2019-11-10\"                  ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"id\": \"999\",                         ";
        s = s + "    \"title\": \"Repeating Event\",          ";
        s = s + "    \"start\": \"2019-11-09T16:00:00-05:00\" ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"id\": \"999\",                         ";
        s = s + "    \"title\": \"Repeating Event\",          ";
        s = s + "    \"start\": \"2019-11-16T16:00:00-05:00\" ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Conference\",               ";
        s = s + "    \"start\": \"2019-11-11\",               ";
        s = s + "    \"end\": \"2019-11-13\"                  ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Meeting\",                  ";
        s = s + "    \"start\": \"2019-11-12T10:30:00-05:00\",";
        s = s + "    \"end\": \"2019-11-12T12:30:00-05:00\"   ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Lunch\",                    ";
        s = s + "    \"start\": \"2019-11-12T12:00:00-05:00\" ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Meeting\",                  ";
        s = s + "    \"start\": \"2019-11-12T14:30:00-05:00\" ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Happy Hour\",               ";
        s = s + "    \"start\": \"2019-11-12T17:30:00-05:00\" ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Dinner\",                   ";
        s = s + "    \"start\": \"2019-11-12T20:00:00\"       ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Birthday Party\",           ";
        s = s + "    \"start\": \"2019-11-13T07:00:00-05:00\" ";
        s = s + "  },                                         ";
        s = s + "  {                                          ";
        s = s + "    \"title\": \"Click for Google\",         ";
        s = s + "    \"url\": \"http://google.com/\",         ";
        s = s + "    \"start\": \"2019-11-28\"                ";
        s = s + "  }                                          ";
        s = s + "]                                            ";
        return s;
    }

}