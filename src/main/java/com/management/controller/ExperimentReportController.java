package com.management.controller;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.model.AjaxUtils;
import com.management.model.ExperimentReport;
import com.management.service.ExperimentReportService;
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
 * @Description: 实验
 * @date 2018/5/9 23:26
 */
@Controller
public class ExperimentReportController {

    @Autowired
    private ExperimentReportService experimentReportService;

    @ModelAttribute("module")
    public String module() {
        return "experimentReport";
    }

    @RequestMapping(value = "experimentReport", method = RequestMethod.GET)
    public String experimentReports(Model model) {
        model.addAttribute("experimentReports", experimentReportService.getAll());
        return "experimentReport/experimentReport-list";
    }

    @RequestMapping(value = "experimentReport/{id}", method = RequestMethod.GET)
    public String experimentReport(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("experimentReport", experimentReportService.get(id));
        return "experimentReport/experimentReport";
    }

    @GetMapping("experimentReport/add")
    public String experimentReportAdd(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new ExperimentReport());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return "experimentReport/experimentReport-add".concat(" :: experimentReportForm");
        }
        return "experimentReport/experimentReport-add";
    }

    @PostMapping("experimentReport/add")
    public String experimentReportAdd(@Valid @ModelAttribute ExperimentReport experimentReport, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "experimentReport/experimentReport-add";
        }
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
    public String experimentReportUpdate(ExperimentReport experimentReport) {
        if (null == experimentReport) {
            return "redirect:/experimentReport";
        }
        experimentReport.setUpdateTime(new Date());
        experimentReportService.update(experimentReport);
        return "redirect:/experimentReport";
    }
}
