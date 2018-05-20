package com.management.controller;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.model.*;
import com.management.service.ExperimentReportService;
import com.management.service.ExperimentService;
import com.management.service.StudentService;
import com.management.service.TeacherService;
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
public class ExperimentReportController {

    @Autowired
    private ExperimentReportService experimentReportService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private TeacherService teacherService;

    @ModelAttribute("module")
    public String module() {
        return "experimentReport";
    }

    @RequestMapping(value = "experimentReport", method = RequestMethod.GET)
    public String experimentReports(Model model, Principal principal) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        String userName = usernamePasswordAuthenticationToken.getName();
        Teacher teacher = teacherService.getByPhone(userName);

        List<ExperimentReport> experimentReports = new ArrayList<>(8);
        List<Experiment> experiments = new ArrayList<>(8);
        if (null != teacher) {
            experiments = experimentService.listByTid(teacher.getTid());
            List<Integer> eids = new ArrayList<>(16);
            for (Experiment experiment : experiments) {
                eids.add(experiment.getEid());
                experimentReports = experimentReportService.listByEids(eids);
            }
        } else {
            Student student = studentService.getBySno(userName);
            if (null != student) {
                experiments = experimentService.listByGid(student.getGid());
                experimentReports = experimentReportService.listBySid(student.getSid());
            }
        }
        model.addAttribute("experimentReports", experimentReports);
        model.addAttribute("experiments", experiments);
        return "experimentReport/experimentReport-list";
    }

    @RequestMapping(value = "experimentReport/{id}", method = RequestMethod.GET)
    public String experimentReport(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("experimentReport", experimentReportService.get(id));
        return "experimentReport/experimentReport";
    }

    @GetMapping("experimentReport/add")
    public String experimentReportAdd(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith, Principal principal) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        String userName = usernamePasswordAuthenticationToken.getName();
        Student student = studentService.getBySno(userName);
        List<Experiment> experiments = null;
        if (null != student) {
            experiments = experimentService.listByGid(student.getGid());
        }
        model.addAttribute(new ExperimentReport());
        model.addAttribute("experiments", experiments);
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "experimentReport/experimentReport-add".concat(" :: experimentReportForm");
        }
        return "experimentReport/experimentReport-add";
    }

    @PostMapping("experimentReport/add")
    public String experimentReportAdd(@Valid @ModelAttribute ExperimentReport experimentReport, Errors errors, RedirectAttributes ra, Principal principal) {
        if (errors.hasErrors()) {
            return "experimentReport/experimentReport-add";
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        String userName = usernamePasswordAuthenticationToken.getName();
        Student student = studentService.getBySno(userName);
        experimentReport.setSid(student.getSid());
        experimentReport.setReadStatus(0);
        experimentReport.setStatus(1);
        experimentReport.setCreateTime(new Date());
        experimentReport.setUpdateTime(new Date());
        experimentReportService.save(experimentReport);
        return "redirect:/experimentReport";
    }

    @RequestMapping(value = "experimentReport/delete/{id}")
    public String experimentReportDelete(@PathVariable("id") Integer id, Model model) {
        experimentReportService.delete(id);
        return "redirect:/experimentReport";
    }

    @GetMapping(value = "experimentReport/update/{id}")
    public String experimentReportUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("experimentReport", experimentReportService.get(id));
        return "experimentReport/experimentReport-update";
    }

    @PostMapping(value = "experimentReport/update")
    public String experimentReportUpdate(ExperimentReport experimentReport, Principal principal) {
        if (null == experimentReport) {
            return "redirect:/experimentReport";
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        String userName = usernamePasswordAuthenticationToken.getName();
        Teacher teacher = teacherService.getByPhone(userName);
        if (null != teacher) {
            experimentReport.setReadStatus(1);
        }
        experimentReport.setUpdateTime(new Date());
        experimentReportService.update(experimentReport);
        return "redirect:/experimentReport";
    }
}
