package com.management.controller;/**
 * Created by jiajia on 2018/5/13.
 */

import com.management.model.AjaxUtils;
import com.management.model.Teacher;
import com.management.service.TeacherService;
import com.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 教师
 * @date 2018/5/13 16:32
 */
@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @ModelAttribute("module")
    public String module() {
        return "teacher";
    }

    @RequestMapping(value = "teacher", method = RequestMethod.GET)
    public String teachers(Model model) {
        model.addAttribute("teachers", teacherService.getAll());
        return "teacher/teacher-list";
    }

    @RequestMapping(value = "teacher/{id}", method = RequestMethod.GET)
    public String teacher(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("teacher", teacherService.get(id));
        return "teacher/teacher";
    }

    @GetMapping("teacher/add")
    public String teacherAdd(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new Teacher());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "teacher/teacher-add".concat(" :: teacherForm");
        }
        return "teacher/teacher-add";
    }

    @PostMapping("teacher/add")
    public String teacherAdd(@Valid @ModelAttribute Teacher teacher, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "teacher/teacher-add";
        }
        teacherService.add(teacher);
        return "redirect:/teacher";
    }

    @RequestMapping(value = "teacher/delete/{id}")
    public String teacherDelete(@PathVariable("id") Integer id, Model model) {
        teacherService.remove(id);
        return "redirect:/teacher";
    }

    @GetMapping(value = "teacher/update/{id}")
    public String teacherUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("teacher", teacherService.get(id));
        return "teacher/teacher-update";
    }

    @PostMapping(value = "teacher/update")
    public String teacherUpdate(Teacher teacher) {
        if (null == teacher) {
            return "redirect:/teacher";
        }
        teacherService.renew(teacher);
        return "redirect:/teacher";
    }
}
