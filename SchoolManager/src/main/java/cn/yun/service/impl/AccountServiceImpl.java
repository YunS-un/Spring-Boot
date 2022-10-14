package cn.yun.service.impl;

import cn.yun.controller.util.R;
import cn.yun.entity.Account;
import cn.yun.entity.Admin;
import cn.yun.entity.Student;
import cn.yun.entity.Teacher;
import cn.yun.mapper.AccountMapper;
import cn.yun.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public R loginSub(Account account, HttpSession session) {
        if (account.getRole() == 1) {
            Student student = new Student(account.getEmail(), account.getPassword(), account.getRole());
            student = studentLogin(student);
            if (student != null) {
                session.setAttribute("account", account);
                session.setAttribute("student", student);
                return new R(true);
            }
        } else if (account.getRole() == 2) {
            Teacher teacher = new Teacher(account.getEmail(), account.getPassword(), account.getRole());
            teacher = teacherLogin(teacher);
            if (teacher != null) {
                session.setAttribute("account", account);
                session.setAttribute("teacher", teacher);
                return new R(true);
            }
        } else {
            Admin admin = new Admin(account.getEmail(), account.getPassword(), account.getRole());
            admin = adminLogin(admin);
            if (admin != null) {
                session.setAttribute("account", account);
                session.setAttribute("admin", admin);
                return new R(true);
            }
        }
        return new R(false);
    }

    @Override
    public R loginEmailSub(Account account, HttpSession session) {
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String mailCode = value.get("mailCode");
        if(mailCode != null && account.getEmailCode().equals(mailCode)){
            if(account.getRole() == 1){
                Student student = new Student(account.getEmail(),account.getRole());
                student = studentCodeLogin(student);
                if (student != null){
                    session.setAttribute("account",account);
                    session.setAttribute("student",student);
                    return new R(true);
                }
            }else if(account.getRole() == 2){
                Teacher teacher = new Teacher(account.getEmail(), account.getRole());
                teacher = teacherCodeLogin(teacher);
                if (teacher != null){
                    session.setAttribute("account",account);
                    session.setAttribute("teacher",teacher);
                    return new R(true);
                }
            }else{
                Admin admin = new Admin(account.getEmail(), account.getRole());
                admin = adminCodeLogin(admin);
                if (admin != null){
                    session.setAttribute("account",account);
                    session.setAttribute("admin",admin);
                    return new R(true);
                }
            }
        }
        return new R(false,"","验证码错误！");
    }

    @Override
    public Student studentLogin(Student student) {
        return accountMapper.selectStudent(student);
    }

    @Override
    public Teacher teacherLogin(Teacher teacher) {
        return accountMapper.selectTeacher(teacher);
    }

    @Override
    public Admin adminLogin(Admin admin) {
        return accountMapper.selectAdmin(admin);
    }

    @Override
    public Student studentCodeLogin(Student student) {
        return accountMapper.selectStudentForCode(student);
    }

    @Override
    public Teacher teacherCodeLogin(Teacher teacher) {
        return accountMapper.selectTeacherForCode(teacher);
    }

    @Override
    public Admin adminCodeLogin(Admin admin) {
        return accountMapper.selectAdminForCode(admin);
    }

    @Override
    public int addSt(Student student) {
        return accountMapper.addOneSt(student);
    }

    @Override
    public int addTc(Teacher teacher) {
        return accountMapper.addOneTc(teacher);
    }

    @Override
    public int modSt(Student student) {
        return accountMapper.modOneSt(student);
    }

    @Override
    public int modTc(Teacher teacher) {
        return accountMapper.modOneTc(teacher);
    }
}
