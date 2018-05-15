package com.management.controller;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.model.AjaxUtils;
import com.management.model.Course;
import com.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 课程
 * @date 2018/5/9 23:26
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ModelAttribute("module")
    public String module() {
        return "course";
    }

    @RequestMapping(value = "course", method = RequestMethod.GET)
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "course/course-list";
    }

    @RequestMapping(value = "course/{id}", method = RequestMethod.GET)
    public String course(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("course", courseService.get(id));
        return "course/course";
    }

    @GetMapping("course/add")
    public String courseAdd(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new Course());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "course/course-add".concat(" :: courseForm");
        }
        return "course/course-add";
    }

    @PostMapping("course/add")
    public String courseAdd(@Valid @ModelAttribute Course course, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "course/course-add";
        }
        course.setStatus(1);
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        courseService.save(course);
        return "redirect:/course";
    }

    @RequestMapping(value = "course/delete/{id}")
    public String courseDelete(@PathVariable("id") Integer id, Model model) {
        courseService.delete(id);
        return "redirect:/course";
    }

    @GetMapping(value = "course/update/{id}")
    public String courseUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("course", courseService.get(id));
        return "course/course-update";
    }

    @PostMapping(value = "course/update")
    public String courseUpdate(Course course) {
        if (null == course) {
            return "redirect:/course";
        }
        course.setUpdateTime(new Date());
        courseService.update(course);
        return "redirect:/course";
    }
}
