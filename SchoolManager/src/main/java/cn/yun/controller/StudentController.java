package cn.yun.controller;

import cn.yun.controller.util.R;
import cn.yun.entity.*;
import cn.yun.service.CourseService;
import cn.yun.service.LeaveService;
import cn.yun.service.StudentService;
import cn.yun.tool.MyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private MyTools myTools;

    //跳转主页
    @RequestMapping("/index")
    public String index(Model model, HttpSession session){
        if(session.getAttribute("student") != null){
            return "student/studentIndex";
        }else if(session.getAttribute("teacher") != null){
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            model.addAttribute("teacher",teacher);
            return "teacher/teacherIndex";
        }else if(session.getAttribute("admin") != null){
            Admin admin = (Admin) session.getAttribute("admin");
            model.addAttribute("admin",admin);
            return "admin/adminStudent";
        }else{
            return "redirect:/login";
        }
    }

    //修改个人信息
    @PostMapping("/studentInfo")
    public String studentInfo(@RequestParam("id") Integer id,
                              @RequestParam("username") String username,
                              @RequestParam("email") String email,
                              @RequestParam("sex") byte sex,
                              @RequestParam("phone") String phone,
                              @RequestParam("birthday") String birthday,
                              @RequestParam("nation") String nation,
                              @RequestParam("address") String address,
                              @RequestParam("imgAddress") MultipartFile img,HttpSession session) throws IOException {
        Student student = new Student(id,username,email,sex,phone,birthday,nation,address);
        if (!img.getOriginalFilename().equals("")){
            String path = myTools.uploadImg(phone, img);
            student.setImgAddress(path);
        }
        studentService.modOne(student);
        session.setAttribute("student",student);
        return "redirect:/index";
    }

    @RequestMapping("/coursePage")
    public String coursePage(){
        return "student/studentCourse";
    }

    //返回课程信息
    @ResponseBody
    @RequestMapping("/getCourseInfo/{studentEmail}")
    public R getCourseInfo(@PathVariable String studentEmail){
        List<CourseInfo> list = courseService.getCourseInfo(studentEmail);
        return new R(true,list);
    }

    //查询课程信息
    @ResponseBody
    @RequestMapping("/getCourse/{studentEmail}/{courseName}")
    public R getCourse(@PathVariable("courseName") String courseName, @PathVariable("studentEmail")String studentEmail){
        List<CourseInfo> list = courseService.selectCourse(studentEmail,courseName);
        return new R(true,list);
    }

    @RequestMapping("/scoresPage")
    public String scoresPage(){
        return "student/studentScores";
    }

    //返回成绩信息
    @ResponseBody
    @RequestMapping("/getScoresInfo/{studentEmail}")
    public R getScoresInfo(@PathVariable String studentEmail){
        List<HashMap> list = courseService.getAllScores(studentEmail);
        return new R(list);
    }

    //查询成绩信息
    @ResponseBody
    @RequestMapping("/getScores/{studentEmail}/{courseName}")
    public R getScores(@PathVariable String studentEmail,@PathVariable String courseName){
        List<HashMap> list = courseService.selectScores(studentEmail,courseName);
        return new R(list);
    }

    @RequestMapping("/leavePage")
    public String leavePage(){
        return "student/studentLeave";
    }

    //返回请假信息
    @ResponseBody
    @RequestMapping("/getLeaveInfo/{studentId}")
    public R getLeaveInfo(@PathVariable Integer studentId){
        List<Leave> leave = leaveService.getLeave(studentId);
        return new R(leave);
    }

    //删除请假消息
    @ResponseBody
    @RequestMapping("/removeLeave/{id}")
    public R removeLeave(@PathVariable Integer id){
        leaveService.removeOne(id);
        return new R(true);
    }

    //根据id获取一条请假消息
    @ResponseBody
    @RequestMapping("/getOne/{id}")
    public R getOne(@PathVariable Integer id){
        Leave leave = leaveService.getOne(id);
        return new R(true,leave);
    }

    //根据id修改一条请假消息
    @ResponseBody
    @RequestMapping("/modOne")
    public R modOne(@RequestBody Leave leave){
        boolean b = leaveService.modOne(leave);
        return new R(b);
    }

    //添加一条请假信息
    @ResponseBody
    @RequestMapping("/addLeave/{studentId}")
    public R addLeave(@RequestBody Leave leave,@PathVariable Integer studentId){
        leave.setStudentId(studentId);
        boolean b = leaveService.addOne(leave);
        return new R(b);
    }

    @RequestMapping("/awardPage")
    public String awardPage(){
        return "student/studentAward";
    }

    @RequestMapping("/punishmentPage")
    public String punishmentPage(){
        return "student/studentPunishment";
    }

    //获取全部评奖信息信息
    @ResponseBody
    @RequestMapping("/getAllAward")
    public R getAllAward(){
        List<AwardInfo> awardInfoAll = studentService.getAwardInfoAll();
        return new R(awardInfoAll);
    }

    //根据名称查询评奖信息信息
    @ResponseBody
    @RequestMapping("/getAwardByName/{name}")
    public R getAwardByName(@PathVariable("name") String name){
        List<AwardInfo> awardInfoByName = studentService.getAwardInfoByName(name);
        return new R(awardInfoByName);
    }

    //根据名称查询评奖信息
    @ResponseBody
    @RequestMapping("/addAward/{info}/{id}")
    public R addAward(@PathVariable("info") String info,@PathVariable("id") Integer id,HttpSession session){
        Student student = (Student) session.getAttribute("student");
        boolean b = studentService.addAward(info, student.getId(), id);
        return new R(b);
    }

    //获取我的评奖信息
    @ResponseBody
    @RequestMapping("/myAward")
    public R myAward(HttpSession session){
        Student student = (Student) session.getAttribute("student");
        List<HashMap> myAward = studentService.getMyAward(student.getId());
        return new R(myAward);
    }

    //获取我的惩罚信息
    @ResponseBody
    @RequestMapping("/getPunishmentStudentId")
    public R getPunishmentStudentId(HttpSession session){
        Student student = (Student) session.getAttribute("student");
        List<Punishment> punishmentByStudentId = studentService.getPunishmentByStudentId(student.getId());
        return new R(punishmentByStudentId);
    }

    //申请撤销惩罚信息
    @ResponseBody
    @RequestMapping("/applyPunishment/{punishmentId}/{info}")
    public R applyPunishment(@PathVariable("punishmentId") Integer punishmentId,@PathVariable("info") String info){
        boolean b = studentService.addPunishment(punishmentId, info);
        return new R(b);
    }

    //获取我申请的惩罚信息
    @ResponseBody
    @RequestMapping("/getApplyPunishment")
    public R getApplyPunishment(HttpSession session){
        Student student = (Student) session.getAttribute("student");
        List<HashMap> applyPunishment = studentService.getApplyPunishment(student.getId());
        return new R(applyPunishment);
    }


}
