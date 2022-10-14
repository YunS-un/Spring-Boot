package cn.yun.mapper;

import cn.yun.entity.Admin;
import cn.yun.entity.Student;
import cn.yun.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {

    Student selectStudent(Student student);

    Teacher selectTeacher(Teacher teacher);

    Admin selectAdmin(Admin admin);

    Student selectStudentForCode(Student student);

    Teacher selectTeacherForCode(Teacher teacher);

    Admin selectAdminForCode(Admin admin);

    int addOneSt(Student student);

    int addOneTc(Teacher teacher);

    int modOneSt(Student student);

    int modOneTc(Teacher teacher);

}
