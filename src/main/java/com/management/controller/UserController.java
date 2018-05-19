package com.management.controller;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.model.AjaxUtils;
import com.management.model.User;
import com.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 班级
 * @date 2018/5/9 23:26
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("module")
    public String module() {
        return "user";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String users(Model model, Principal principal) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        String userName = usernamePasswordAuthenticationToken.getName();
        User user = userService.getByLoginName(userName);
        List<User> users = new ArrayList<>(8);
        users.add(user);
        model.addAttribute("users", users);
        return "user/user-list";
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public String user(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "user/user";
    }

    @GetMapping("user/add")
    public String userAdd(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new User());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "user/user-add".concat(" :: userForm");
        }
        return "user/user-add";
    }

    @PostMapping("user/add")
    public String userAdd(@Valid @ModelAttribute User user, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "user/user-add";
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.save(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "user/delete/{id}")
    public String userDelete(@PathVariable("id") Integer id, Model model) {
        userService.delete(id);
        return "redirect:/user";
    }

    @GetMapping(value = "user/update/{id}")
    public String userUpdate(@PathVariable("id") Integer id, Model model) {
        User user = userService.get(id);
        user.setPasswd(null);
        model.addAttribute("user", user);
        return "user/user-update";
    }

    @PostMapping(value = "user/update")
    public String userUpdate(User user) {
        if (null == user) {
            return "redirect:/user";
        }
        if (!user.getNewpasswd().equals(user.getRepasswd())) {
            return "redirect:/user";
        }
        userService.updatePasswd(user);
        return "redirect:/user";
    }
}
