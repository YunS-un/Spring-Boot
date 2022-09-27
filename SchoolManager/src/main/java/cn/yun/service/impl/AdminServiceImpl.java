package cn.yun.service.impl;

import cn.yun.entity.Student;
import cn.yun.entity.Teacher;
import cn.yun.mapper.AdminMapper;
import cn.yun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean modStudent(Student student) {
        return adminMapper.updateStudentById(student);
    }

    @Override
    public boolean modTeacher(Teacher teacher) {
        return adminMapper.updateTeacherById(teacher);
    }

    @Override
    public List<Student> getAllStudent() {
        return adminMapper.selectAllStudent();
    }

    @Override
    public List<Student> getStudentLikeStudentName(String username) {
        List<Student> allStudent = getAllStudent();
        List<Student> list = new ArrayList<>();
        for (Student student: allStudent) {
            if (student.getUsername().contains(username)){
                list.add(student);
            }
        }
        return list;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return adminMapper.selectAllTeacher();
    }

    @Override
    public List<Teacher> getTeacherLikeTeacherName(String username) {
        List<Teacher> allTeacher = getAllTeacher();
        List<Teacher> list = new ArrayList<>();
        for (Teacher teacher: allTeacher) {
            if (teacher.getUsername().contains(username)){
                list.add(teacher);
            }
        }
        return list;
    }
}
