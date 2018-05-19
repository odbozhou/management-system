package com.management.controller.home;

import com.management.model.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

@Controller
class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @RequestMapping("/")
    String index(Principal principal, Model model) {
        if (principal == null) {
            return "home/homeNotSignedIn";
        }
//        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        Collection<GrantedAuthority> authorities = usernamePasswordAuthenticationToken.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            logger.info("Authority {}", grantedAuthority.getAuthority());
            if ("ROLE_ADMIN".equals(grantedAuthority.getAuthority())) {
                List<Menu> menus = new ArrayList<>(16);
                Menu menu1 = new Menu();
                menu1.setName("课程信息管理");
                menu1.setUrl("/course");
                menu1.setModule("course");
                Menu menu2 = new Menu();
                menu2.setName("教师信息管理");
                menu2.setUrl("/teacher");
                menu2.setModule("teacher");
                Menu menu3 = new Menu();
                menu3.setName("实验管理");
                menu3.setUrl("/experiment");
                menu3.setModule("experiment");
                Menu menu5 = new Menu();
                menu5.setName("学生信息管理");
                menu5.setUrl("/student");
                menu5.setModule("student");
                Menu menu4 = new Menu();
                menu4.setName("Home");
                menu4.setUrl("/");
                menu4.setModule("home");
                Menu menu6 = new Menu();
                menu6.setName("班级信息管理");
                menu6.setUrl("/grade");
                menu6.setModule("grade");
                menus.add(menu4);
                menus.add(menu5);
                menus.add(menu6);
                menus.add(menu1);
                menus.add(menu2);
                menus.add(menu3);
                model.addAttribute("menus", menus);
                return "home/adminHomeSignedIn";
            } else if ("ROLE_TEACHER".equals(grantedAuthority.getAuthority())) {
                List<Menu> menus = new ArrayList<>(16);
                Menu menu = new Menu();
                menu.setName("Home");
                menu.setUrl("/");
                menu.setModule("home");
                Menu menu1 = new Menu();
                menu1.setName("实验管理");
                menu1.setUrl("/experiment");
                menu1.setModule("experiment");
                Menu menu2 = new Menu();
                menu2.setName("实验报告管理");
                menu2.setUrl("/experimentReport");
                menu2.setModule("experimentReport");
                Menu menu3 = new Menu();
                menu3.setName("个人信息");
                menu3.setUrl("/userInfo");
                menu3.setModule("userInfo");
                menus.add(menu);
                menus.add(menu1);
                menus.add(menu2);
                menus.add(menu3);
                model.addAttribute("menus", menus);
                return "home/teacherHomeSignedIn";
            } else if ("ROLE_STUDENT".equals(grantedAuthority.getAuthority())) {
                List<Menu> menus = new ArrayList<>(16);
                Menu menu = new Menu();
                menu.setName("Home");
                menu.setUrl("/");
                menu.setModule("home");
                Menu menu1 = new Menu();
                menu1.setName("实验管理");
                menu1.setUrl("/experiment");
                menu1.setModule("experiment");
                Menu menu2 = new Menu();
                menu2.setName("实验报告管理");
                menu2.setUrl("/experimentReport");
                menu2.setModule("experimentReport");
                Menu menu3 = new Menu();
                menu3.setName("个人信息");
                menu3.setUrl("/userInfo");
                menu3.setModule("userInfo");
                menus.add(menu);
                menus.add(menu1);
                menus.add(menu2);
                menus.add(menu3);
                model.addAttribute("menus", menus);
                return "home/studentHomeSignedIn";
            }
        }
        return "home/homeNotSignedIn";
    }
}
