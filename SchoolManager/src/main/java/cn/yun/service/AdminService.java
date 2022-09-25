package cn.yun.service;

import cn.yun.entity.Student;
import cn.yun.entity.Teacher;

import java.util.List;

public interface AdminService {

    boolean modStudent(Student student);

    boolean modTeacher(Teacher teacher);

    List<Student> getAllStudent();

    List<Student> getStudentLikeStudentName(String username);

    List<Teacher> getAllTeacher();

    List<Teacher> getTeacherLikeTeacherName(String username);

}
