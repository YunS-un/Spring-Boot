package cn.yun.mapper;

import cn.yun.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {

    boolean updateById(Teacher teacher);

    boolean updateClazz(Clazz clazz);

    boolean updateCourse(CourseInfo courseInfo);

    boolean updateCourseForScores(Course course);

    boolean updateLeaveForClazz(Leave leave);

    boolean updateAwardForClazz(Award award);

    boolean updatePunishForClazz(PunishmentInfo punishmentInfo);

    List<CourseInfo> selectCourseByTeacherId(Integer id);

    CourseInfo selectCourseById(Integer id);

    List<PunishmentInfo> selectPunishByStudentId(List<Integer> idList);

    List<Award> selectAwardByStudentId(List<Integer> idList);

    List<Clazz> selectStudentByTeacherId(Integer id);

    List<Clazz> selectByTeacherId(Integer id);

    List<Course> selectCourseInfoForTeacher(List<Integer> idList);

    List<Leave> selectLeaveByStudentIdForClazz(List<Integer> idList);

    Clazz selectById(Integer id);

    List<Clazz> selectLikeName(String name);

    List<Clazz> selectClazzName();

}
