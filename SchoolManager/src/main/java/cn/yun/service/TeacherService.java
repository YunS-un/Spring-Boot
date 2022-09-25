package cn.yun.service;

import cn.yun.entity.*;

import java.util.List;

public interface TeacherService {

    boolean modById(Teacher teacher);

    boolean modClazz(Clazz clazz);

    boolean modTeacherCourse(CourseInfo courseInfo);

    boolean modCourseForScores(Course course);

    boolean modLeaveForClazz(Leave leave);

    boolean modAwardForClazz(Award award);

    boolean modPunishForClazz(PunishmentInfo punishmentInfo);

    Clazz getClazzById(Integer id);

    List<CourseInfo> getCourseByTeacherId(Integer id);

    List<CourseInfo> getCourseLikeName(Integer id,String name);

    List<PunishmentInfo> getPunishByStudentId(List<Integer> idList);

    List<PunishmentInfo> getPunishByStudentName(Integer id,String name);

    List<Award> getAwardByStudentId(List<Integer> idList);

    List<Award> getAwardByStudentName(Integer id,String name);

    List<Leave> getLeaveByClazzStudentId(List<Integer> idList);

    List<Leave> getLeaveByStudentName(Integer id,String name);

    CourseInfo getCourseById(Integer id);

    List<Clazz> getLikeName(String name);

    List<Clazz> getClazzName();

    List<Clazz> getStudentByTeacherId(Integer id);

    List<Clazz> getByTeacherId(Integer id);

    List<Integer> getStudentIdForClazz(Integer teacherId);

    List<Course> getCourseInfoForTeacher(List<Integer> idList);

    List<Course> getCourseByStuNameAndCsName(String stuName,String csName,Integer teacherId);

    List<Object> getStudentInfo(Integer id);

    List<Student> getStudentForName(Integer id, String studentName, String clazzName);

}
