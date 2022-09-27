package cn.yun.service;

import cn.yun.entity.Admin;
import cn.yun.entity.Student;
import cn.yun.entity.Teacher;

public interface AccountService {

    Student studentLogin(Student student);

    Teacher teacherLogin(Teacher teacher);

    Admin adminLogin(Admin admin);

    int addSt(Student student);

    int addTc(Teacher teacher);

    int modSt(Student student);

    int modTc(Teacher teacher);

}
