package cn.yun.mapper;

import cn.yun.entity.Student;
import cn.yun.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {

    boolean updateStudentById(Student student);

    boolean updateTeacherById(Teacher teacher);

    List<Student> selectAllStudent();

    List<Teacher> selectAllTeacher();

}
