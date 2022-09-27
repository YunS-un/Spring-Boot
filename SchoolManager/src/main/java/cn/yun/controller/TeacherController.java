package cn.yun.controller;

import cn.yun.controller.util.R;
import cn.yun.entity.*;
import cn.yun.service.TeacherService;
import cn.yun.tool.MyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MyTools myTools;

    @RequestMapping("/studentInfoPage")
    public String studentInfoPage(){
        return "teacher/teacherClazzStudent";
    }

    @RequestMapping("/clazzPage")
    public String clazzPage(){
        return "teacher/teacherClazz";
    }

    @RequestMapping("/teacherCoursePage")
    public String teacherCoursePage(){
        return "teacher/teacherCourse";
    }

    @RequestMapping("/teacherScoresPage")
    public String teacherScoresPage(){
        return "teacher/teacherScores";
    }

    @RequestMapping("/teacherLeavePage")
    public String teacherLeavePage(){
        return "teacher/teacherLeave";
    }

    @RequestMapping("/teacherAwardPage")
    public String teacherAwardPage(){
        return "teacher/teacherAward";
    }

    @RequestMapping("/teacherPunishmentPage")
    public String teacherPunishmentPage(){
        return "teacher/teacherPunishment";
    }

    //老师个人信息修改
    @RequestMapping("/modTeacher")
    public String  modTeacher(@RequestParam("id") Integer id,
                        @RequestParam("username") String username,
                        @RequestParam("email") String email,
                        @RequestParam("sex") byte sex,
                        @RequestParam("phone") String phone,
                        @RequestParam("birthday") String birthday,
                        @RequestParam("nation") String nation,
                        @RequestParam("address") String address,
                        @RequestParam("imgAddress") MultipartFile img, HttpSession session) throws IOException {
        Teacher teacher = new Teacher(id,username,email,sex,phone,birthday,nation,address);
        if (!img.getOriginalFilename().equals("")){
            String path = myTools.uploadImg(phone, img);
            teacher.setImgAddress(path);
        }
        teacherService.modById(teacher);
        session.setAttribute("teacher",teacher);
        return "redirect:/index";
    }

    //学生信息获取
    @ResponseBody
    @RequestMapping("/studentForClazz/{id}")
    public R studentForClazz(@PathVariable Integer id){
        List<Object> list = teacherService.getStudentInfo(id);
        return new R(list);
    }

    //班级信息下拉框加载
    @ResponseBody
    @RequestMapping("/getClazzName")
    public R getClazzName(){
        List<Clazz> list = teacherService.getClazzName();
        return new R(list);
    }

    //学生信息条件查询
    @ResponseBody
    @RequestMapping("/getStudentByName")
    public R getStudentByName(@RequestParam("teacherId") Integer teacherId,
                              @RequestParam("studentName")String studentName,
                              @RequestParam("clazzName")String clazzName){
        List<Student> student = teacherService.getStudentForName(teacherId, studentName,clazzName);
        return new R(student);
    }

    //班级信息获取
    @ResponseBody
    @RequestMapping("/getClazzInfo/{teacherId}")
    public R getClazzInfo(@PathVariable("teacherId") Integer id){
        List<Clazz> byTeacherId = teacherService.getStudentByTeacherId(id);
        return new R(byTeacherId);
    }

    //班级信息模糊查询
    @ResponseBody
    @RequestMapping("/getClazzLikeName/{clazzName}")
    public R getClazzLikeName(@PathVariable("clazzName") String clazzName){
        List<Clazz> clazzList = teacherService.getLikeName(clazzName);
        return new R(clazzList);
    }

    //根据id获取班级信息
    @ResponseBody
    @RequestMapping("/getClazzById/{clazzId}")
    public R getClazzById(@PathVariable("clazzId") Integer clazzId){
        Clazz clazz = teacherService.getClazzById(clazzId);
        return new R(clazz);
    }

    //修改班级信息
    @ResponseBody
    @RequestMapping("/modClazz")
    public R modClazz(@RequestBody Clazz clazz){
        boolean b = teacherService.modClazz(clazz);
        return new R(b);
    }

    //修改班级信息
    @ResponseBody
    @RequestMapping("/getTeacherCourseInfo/{id}")
    public R getTeacherCourseInfo(@PathVariable("id") Integer id){
        List<CourseInfo> courseByTeacherId = teacherService.getCourseByTeacherId(id);
        return new R(courseByTeacherId);
    }

    //模糊查询课程信息
    @ResponseBody
    @RequestMapping("/getTeacherCourseInfoLikeName/{courseName}/{teacherId}")
    public R getTeacherCourseInfoLikeName(@PathVariable("courseName") String courseName,@PathVariable("teacherId") Integer teacherId){
        List<CourseInfo> courseLikeName = teacherService.getCourseLikeName(teacherId,courseName);
        return new R(courseLikeName);
    }

    //根据id获取课程信息
    @ResponseBody
    @RequestMapping("/getTeacherCourseInfoById/{id}")
    public R getTeacherCourseInfoById(@PathVariable("id") Integer id){
        CourseInfo courseById = teacherService.getCourseById(id);
        return new R(courseById);
    }

    //修改课程信息
    @ResponseBody
    @RequestMapping("/modTeacherCourse")
    public R modTeacherCourse(@RequestBody CourseInfo courseInfo){
        boolean b = teacherService.modTeacherCourse(courseInfo);
        return new R(b);
    }

    //获取学生课程信息
    @ResponseBody
    @RequestMapping("/getTeacherScores")
    public R getTeacherScores(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Integer> idList = teacherService.getStudentIdForClazz(teacher.getId());
        List<Course> courseList = teacherService.getCourseInfoForTeacher(idList);
        return new R(courseList);
    }

    //获取学生课程信息
    @ResponseBody
    @RequestMapping("/getCourseByCsNameAndStuName/{stuName}/{csName}")
    public R getCourseByCsNameAndStuName(@PathVariable("stuName") String stuName,@PathVariable("csName") String csName,HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Course> course = teacherService.getCourseByStuNameAndCsName(stuName, csName, teacher.getId());
        return new R(course);
    }

    //获取学生课程信息
    @ResponseBody
    @RequestMapping("/modTeaCourseForScores")
    public R modTeaCourseForScores(@RequestBody Course course){
        boolean b = teacherService.modCourseForScores(course);
        return new R(b);
    }

    //获取学生请假信息
    @ResponseBody
    @RequestMapping("/getLeaveForClazzStuId")
    public R getLeaveForClazzStuId(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Integer> list = teacherService.getStudentIdForClazz(teacher.getId());
        List<Leave> leaves = teacherService.getLeaveByClazzStudentId(list);
        return new R(leaves);
    }

    //审核学生请假信息
    @ResponseBody
    @RequestMapping("/modLeaveForClazz")
    public R modLeaveForClazz(@RequestBody Leave leave){
        boolean b = teacherService.modLeaveForClazz(leave);
        return new R(b);
    }

    //查询学生请假信息
    @ResponseBody
    @RequestMapping("/getLeaveForStudentName/{userName}")
    public R getLeaveForStudentName(@PathVariable("userName") String userName, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Leave> leave = teacherService.getLeaveByStudentName(teacher.getId(), userName);
        return new R(leave);
    }

    //获取学生奖励信息
    @ResponseBody
    @RequestMapping("/getAwardForStudentId")
    public R getAwardForStudentId(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Integer> id = teacherService.getStudentIdForClazz(teacher.getId());
        List<Award> list = teacherService.getAwardByStudentId(id);
        return new R(list);
    }

    //查询学生请假信息
    @ResponseBody
    @RequestMapping("/getAwardForStudentName/{userName}")
    public R getAwardForStudentName(@PathVariable("userName") String userName, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Award> list = teacherService.getAwardByStudentName(teacher.getId(),userName);
        return new R(list);
    }

    //审核学生奖励信息
    @ResponseBody
    @RequestMapping("/modAwardForClazz")
    public R modAwardForClazz(@RequestBody Award award){
        boolean b = teacherService.modAwardForClazz(award);
        return new R(b);
    }

    //获取学生处分信息
    @ResponseBody
    @RequestMapping("/getPunishForStudentId")
    public R getPunishForStudentId(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Integer> id = teacherService.getStudentIdForClazz(teacher.getId());
        List<PunishmentInfo> list = teacherService.getPunishByStudentId(id);
        return new R(list);
    }

    //查询学生处分信息
    @ResponseBody
    @RequestMapping("/getPunishForStudentName/{userName}")
    public R getPunishForStudentName(@PathVariable("userName") String userName, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<PunishmentInfo> list = teacherService.getPunishByStudentName(teacher.getId(),userName);
        return new R(list);
    }

    //审核学生处分信息
    @ResponseBody
    @RequestMapping("/modPunishForClazz")
    public R modPunishForClazz(@RequestBody PunishmentInfo punish){
        boolean b = teacherService.modPunishForClazz(punish);
        return new R(b);
    }





}
