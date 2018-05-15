package com.management.controller;/**
 * Created by jiajia on 2018/5/13.
 */

import com.management.model.AjaxUtils;
import com.management.model.Student;
import com.management.service.GradeService;
import com.management.service.StudentService;
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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private GradeService gradeService;

    @ModelAttribute("module")
    public String module() {
        return "student";
    }

    @RequestMapping(value = "student", method = RequestMethod.GET)
    public String students(Model model) {
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("grades", gradeService.getAll());
        return "student/student-list";
    }

    @RequestMapping(value = "student/{id}", method = RequestMethod.GET)
    public String student(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("student", studentService.get(id));
        model.addAttribute("grades", gradeService.getAll());
        return "student/student";
    }

    @GetMapping("student/add")
    public String studentAdd(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new Student());
        model.addAttribute("grades", gradeService.getAll());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "student/student-add".concat(" :: studentForm");
        }
        return "student/student-add";
    }

    @PostMapping("student/add")
    public String studentAdd(@Valid @ModelAttribute Student student, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "student/student-add";
        }
        studentService.add(student);
        return "redirect:/student";
    }

    @RequestMapping(value = "student/delete/{id}")
    public String studentDelete(@PathVariable("id") Integer id, Model model) {
        studentService.remove(id);
        return "redirect:/student";
    }

    @GetMapping(value = "student/update/{id}")
    public String studentUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("student", studentService.get(id));
        model.addAttribute("grades", gradeService.getAll());
        return "student/student-update";
    }

    @PostMapping(value = "student/update")
    public String studentUpdate(Student student) {
        if (null == student) {
            return "redirect:/student";
        }
        studentService.renew(student);
        return "redirect:/student";
    }
}
