package cn.yun;

import cn.yun.controller.util.R;
import cn.yun.entity.*;
import cn.yun.mapper.AccountMapper;
import cn.yun.mapper.StudentMapper;
import cn.yun.service.AccountService;
import cn.yun.service.SendMailCodeService;
import cn.yun.service.StudentService;
import cn.yun.service.TeacherService;
import cn.yun.tool.MyTools;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SchoolManagerApplicationTests {

    @Autowired
    private SendMailCodeService sendMailCodeService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MyTools myTools;

    @Autowired
    private AccountService accountService;

    @Test
    void contextLoads() {
        Student student = new Student("17898762356@163.com","123456",1);
        student.setUsername("张三丰");
        student.setBirthday("2021-08-06");
        accountService.addSt(student);
    }

    @Test
    void cour() {
        List<Integer> id = teacherService.getStudentIdForClazz(2);
        List<PunishmentInfo> list = teacherService.getPunishByStudentId(id);
        System.err.println(list);
    }

    @Test
    void getCode(){
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        System.out.println(value.get("mailCode"));
    }

}
