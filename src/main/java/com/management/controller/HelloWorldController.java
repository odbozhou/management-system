package com.management.controller;/**
 * Created by jiajia on 2018/5/9.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiajia
 * @version V1.0
 * @Description: hello world
 * @date 2018/5/9 0:07
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/sayHello")
    public Object sayHello() {
        return "hello world";
    }

    @RequestMapping("/index")
    public Object index(Model model) {
        model.addAttribute("name", "Dear");
//        return "securityPage/main";
        return "securityPage/test";
    }

}
