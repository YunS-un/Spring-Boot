package cn.yun.service.impl;

import cn.yun.entity.Admin;
import cn.yun.entity.Student;
import cn.yun.entity.Teacher;
import cn.yun.mapper.AccountMapper;
import cn.yun.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

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
