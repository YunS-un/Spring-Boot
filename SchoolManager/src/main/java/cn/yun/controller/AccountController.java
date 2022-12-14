package cn.yun.controller;

import cn.yun.controller.util.R;
import cn.yun.entity.Account;
import cn.yun.entity.Admin;
import cn.yun.entity.Student;
import cn.yun.entity.Teacher;
import cn.yun.service.AccountService;
import cn.yun.service.SendMailCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SendMailCodeService mailCodeService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public String login(HttpSession session){
        session.removeAttribute("account");
        session.removeAttribute("student");
        session.removeAttribute("teacher");
        session.removeAttribute("admin");
        return "login";
    }

    @RequestMapping("/loginEmail")
    public String loginEmail(HttpSession session){
        return "loginEmail";
    }

    //登陆判断
    @RequestMapping("/handle/loginSubmit")
    @ResponseBody
    public R loginSubmit(@RequestBody Account account, HttpSession session){
        R r = accountService.loginSub(account, session);
        return r;
    }

    @RequestMapping("/handle/loginEmailSubmit")
    @ResponseBody
    public R loginEmailSubmit(@RequestBody Account account, HttpSession session){
        R r = accountService.loginEmailSub(account, session);
        return r;
    }

    @GetMapping(value = "/handle/getEmailCode/{email}")
    @ResponseBody
    public R getEmailCode(@PathVariable String email){
        return new R(true,mailCodeService.sendCode(email));
    }

    @RequestMapping("/handle/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/handle/studentRegister")
    public String studentRegister(){
        return "/student/studentRegister";
    }

    @RequestMapping("/handle/studentRgsSubmit")
    @ResponseBody
    public R studentRgsSubmit(@RequestBody Student student){
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String mailCode = value.get("mailCode");
        if(mailCode == null || !student.getEmailCode().equals(mailCode)){
            return new R(false,null,"验证码已过期或验证码错误，请重新获取验证码。");
        }else if(student.getEmailCode().equals(mailCode)){
            accountService.addSt(student);
            return new R(true);
        }else{
            return new R(false,null,"信息错误，请重新获取验证码。");
        }
    }

    @RequestMapping("/handle/teacherRegister")
    public String teacherRegister(){
        return "/teacher/teacherRegister";
    }

    @RequestMapping("/handle/teacherRgsSubmit")
    @ResponseBody
    public R teacherRgsSubmit(@RequestBody Teacher teacher){
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String mailCode = value.get("mailCode");
        if(mailCode == null || !teacher.getEmailCode().equals(mailCode)){
            return new R(false,null,"验证码已过期或验证码错误，请重新获取验证码。");
        }else if(teacher.getEmailCode().equals(mailCode)){
            accountService.addTc(teacher);
            return new R(true);
        }else{
            return new R(false,null,"信息错误，请重新获取验证码。");
        }
    }

    @RequestMapping("/handle/forgetPwd")
    public String forgetPwd(){
        return "forgetPwd";
    }

    @RequestMapping("/handle/studentForgetPwd")
    public String studentforgetPwd(){
        return "/student/studentForgetPwd";
    }

    @RequestMapping("/handle/studentForgetPwdSub")
    @ResponseBody
    public R studentForgetPwdSub(@RequestBody Student student){
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String mailCode = value.get("mailCode");
        if(mailCode == null || !student.getEmailCode().equals(mailCode)){
            return new R(false,null,"验证码已过期或验证码错误，请重新获取验证码。");
        }else if(student.getEmailCode().equals(mailCode)){
            accountService.modSt(student);
            return new R(true);
        }else{
            return new R(false,null,"信息错误，请重新获取验证码。");
        }
    }

    @RequestMapping("/handle/teacherForgetPwd")
    public String teacherforgetPwd(){
        return "/teacher/teacherForgetPwd";
    }

    @RequestMapping("/handle/teacherForgetPwdSub")
    @ResponseBody
    public R teacherForgetPwdSub(@RequestBody Teacher teacher){
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String mailCode = value.get("mailCode");
        if(mailCode == null || !teacher.getEmailCode().equals(mailCode)){
            return new R(false,null,"验证码已过期或验证码错误，请重新获取验证码。");
        }else if(teacher.getEmailCode().equals(mailCode)){
            accountService.modTc(teacher);
            return new R(true);
        }else{
            return new R(false,null,"信息错误，请重新获取验证码。");
        }
    }


}
