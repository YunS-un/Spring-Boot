package cn.yun.controller;

import cn.yun.controller.util.R;
import cn.yun.entity.Student;
import cn.yun.entity.Teacher;
import cn.yun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/adminStudent")
    public String adminStudent(){
        return "admin/adminStudent";
    }

    @RequestMapping("/adminTeacher")
    public String adminTeacher(){
        return "admin/adminTeacher";
    }

    @ResponseBody
    @RequestMapping("getStudentAll")
    public R getStudentAll(){
        return new R(adminService.getAllStudent());
    }

    @ResponseBody
    @RequestMapping("getTeacherAll")
    public R getTeacherAll(){
        return new R(adminService.getAllTeacher());
    }

    @ResponseBody
    @RequestMapping("getStudentLikeStudentName/{userName}")
    public R getStudentLikeStudentName(@PathVariable("userName")String name){
        return new R(adminService.getStudentLikeStudentName(name));
    }

    @ResponseBody
    @RequestMapping("getTeacherLikeTeacherName/{userName}")
    public R getTeacherLikeTeacherName(@PathVariable("userName")String name){
        return new R(adminService.getTeacherLikeTeacherName(name));
    }

    @ResponseBody
    @RequestMapping("modStudentByStudentId")
    public R modStudentByStudentId(@RequestBody Student student){
        return new R(adminService.modStudent(student));
    }

    @ResponseBody
    @RequestMapping("modTeacherByTeacherId")
    public R modTeacherByTeacherId(@RequestBody Teacher teacher){
        return new R(adminService.modTeacher(teacher));
    }



}
