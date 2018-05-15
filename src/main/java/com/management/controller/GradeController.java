package com.management.controller;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.model.AjaxUtils;
import com.management.model.Grade;
import com.management.service.GradeService;
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
 * @Description: 班级
 * @date 2018/5/9 23:26
 */
@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @ModelAttribute("module")
    public String module() {
        return "grade";
    }

    @RequestMapping(value = "grade", method = RequestMethod.GET)
    public String grades(Model model) {
        model.addAttribute("grades", gradeService.getAll());
        return "grade/grade-list";
    }

    @RequestMapping(value = "grade/{id}", method = RequestMethod.GET)
    public String grade(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("grade", gradeService.get(id));
        return "grade/grade";
    }

    @GetMapping("grade/add")
    public String gradeAdd(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new Grade());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "grade/grade-add".concat(" :: gradeForm");
        }
        return "grade/grade-add";
    }

    @PostMapping("grade/add")
    public String gradeAdd(@Valid @ModelAttribute Grade grade, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "grade/grade-add";
        }
        grade.setCreateTime(new Date());
        grade.setUpdateTime(new Date());
        gradeService.save(grade);
        return "redirect:/grade";
    }

    @RequestMapping(value = "grade/delete/{id}")
    public String gradeDelete(@PathVariable("id") Integer id, Model model) {
        gradeService.delete(id);
        return "redirect:/grade";
    }

    @GetMapping(value = "grade/update/{id}")
    public String gradeUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("grade", gradeService.get(id));
        return "grade/grade-update";
    }

    @PostMapping(value = "grade/update")
    public String gradeUpdate(Grade grade) {
        if (null == grade) {
            return "redirect:/grade";
        }
        grade.setUpdateTime(new Date());
        gradeService.update(grade);
        return "redirect:/grade";
    }
}
