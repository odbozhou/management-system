package com.management.controller;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.model.AjaxUtils;
import com.management.model.Experiment;
import com.management.model.Student;
import com.management.model.Teacher;
import com.management.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Description: 实验
 * @date 2018/5/9 23:26
 */
@Controller
public class ExperimentController {

    private static final Logger logger = LoggerFactory.getLogger(ExperimentController.class);

    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @ModelAttribute("module")
    public String module() {
        return "experiment";
    }

    @RequestMapping(value = "experiment", method = RequestMethod.GET)
    public String experiments(Model model, Principal principal) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        String userName = usernamePasswordAuthenticationToken.getName();
        Teacher teacher = teacherService.getByPhone(userName);
        List<Experiment> experiments = new ArrayList<>(8);
        if (null != teacher) {
            experiments = experimentService.listByTid(teacher.getTid());
        } else {
            Student student = studentService.getBySno(userName);
            if (null != student) {
                experiments = experimentService.listByGid(student.getGid());
            }
        }
        model.addAttribute("experiments", experiments);
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("grades", gradeService.getAll());
        return "experiment/experiment-list";
    }

    @RequestMapping(value = "experiment/{id}", method = RequestMethod.GET)
    public String experiment(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("experiment", experimentService.get(id));
        return "experiment/experiment";
    }

    @GetMapping("experiment/add")
    public String experimentAdd(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new Experiment());
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("grades", gradeService.getAll());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "experiment/experiment-add".concat(" :: experimentForm");
        }
        return "experiment/experiment-add";
    }

    @PostMapping("experiment/add")
    public String experimentAdd(@Valid @ModelAttribute Experiment experiment, Errors errors, RedirectAttributes ra, Principal principal) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        String userName = usernamePasswordAuthenticationToken.getName();
        Teacher teacher = teacherService.getByPhone(userName);
        logger.info("current userName {}", userName);
        if (errors.hasErrors()) {
            return "experiment/experiment-add";
        }
        experiment.setTime(experiment.getTime());
        experiment.setTid(teacher.getTid());
        experiment.setCreateTime(new Date());
        experiment.setUpdateTime(new Date());
        experiment.setStatus(1);
        experimentService.save(experiment);
        return "redirect:/experiment";
    }

    @RequestMapping(value = "experiment/delete/{id}")
    public String experimentDelete(@PathVariable("id") Integer id, Model model) {
        experimentService.delete(id);
        return "redirect:/experiment";
    }

    @GetMapping(value = "experiment/update/{id}")
    public String experimentUpdate(@PathVariable("id") Integer id, Model model) {
        Experiment experiment = experimentService.get(id);
        experiment.setTimeString(experiment.getTimeString());
        model.addAttribute("experiment", experiment);
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("grades", gradeService.getAll());
        return "experiment/experiment-update";
    }

    @PostMapping(value = "experiment/update")
    public String experimentUpdate(Experiment experiment) {
        if (null == experiment) {
            return "redirect:/experiment";
        }
        experiment.setTime(experiment.getTime());
        experiment.setUpdateTime(new Date());
        experimentService.update(experiment);
        return "redirect:/experiment";
    }
}
