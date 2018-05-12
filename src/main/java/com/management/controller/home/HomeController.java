package com.management.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @RequestMapping("/")
    String index(Principal principal) {
        logger.info("principal {}", principal.toString());
        if (principal == null) {
            return "home/homeNotSignedIn";
        }
//        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        Collection<GrantedAuthority> authorities = usernamePasswordAuthenticationToken.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            logger.info("Authority {}", grantedAuthority.getAuthority());
            if ("ROLE_ADMIN".equals(grantedAuthority.getAuthority())) {
                return "home/adminHomeSignedIn";
            } else if ("ROLE_TEACHER".equals(grantedAuthority.getAuthority())) {
                return "home/teacherHomeSignedIn";
            } else if ("ROLE_STUDENT".equals(grantedAuthority.getAuthority())) {
                return "home/studentHomeSignedIn";
            }
        }
        return "home/homeNotSignedIn";
    }
}
