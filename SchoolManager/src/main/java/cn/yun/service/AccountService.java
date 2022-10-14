package cn.yun.service;

import cn.yun.controller.util.R;
import cn.yun.entity.Account;
import cn.yun.entity.Admin;
import cn.yun.entity.Student;
import cn.yun.entity.Teacher;

import javax.servlet.http.HttpSession;

public interface AccountService {

    R loginSub(Account account, HttpSession session);

    R loginEmailSub(Account account, HttpSession session);

    Student studentLogin(Student student);

    Teacher teacherLogin(Teacher teacher);

    Admin adminLogin(Admin admin);

    Student studentCodeLogin(Student student);

    Teacher teacherCodeLogin(Teacher teacher);

    Admin adminCodeLogin(Admin admin);

    int addSt(Student student);

    int addTc(Teacher teacher);

    int modSt(Student student);

    int modTc(Teacher teacher);

}
