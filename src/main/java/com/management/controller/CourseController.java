package com.management.controller;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 课程
 * @date 2018/5/9 23:26
 */
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/list")
    public Object list(){
        return courseService.getAll();
    }
}
