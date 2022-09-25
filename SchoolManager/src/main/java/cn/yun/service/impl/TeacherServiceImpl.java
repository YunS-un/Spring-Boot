package cn.yun.service.impl;

import cn.yun.entity.*;
import cn.yun.mapper.TeacherMapper;
import cn.yun.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public boolean modById(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }

    @Override
    public boolean modClazz(Clazz clazz) {
        return teacherMapper.updateClazz(clazz);
    }

    @Override
    public boolean modTeacherCourse(CourseInfo courseInfo) {
        return teacherMapper.updateCourse(courseInfo);
    }

    @Override
    public boolean modCourseForScores(Course course) {
        return teacherMapper.updateCourseForScores(course);
    }

    @Override
    public boolean modLeaveForClazz(Leave leave) {
        return teacherMapper.updateLeaveForClazz(leave);
    }

    @Override
    public boolean modAwardForClazz(Award award) {
        return teacherMapper.updateAwardForClazz(award);
    }

    @Override
    public boolean modPunishForClazz(PunishmentInfo punishmentInfo) {
        return teacherMapper.updatePunishForClazz(punishmentInfo);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        return teacherMapper.selectById(id);
    }

    @Override
    public List<CourseInfo> getCourseByTeacherId(Integer id) {
        return teacherMapper.selectCourseByTeacherId(id);
    }

    @Override
    public List<CourseInfo> getCourseLikeName(Integer id,String name) {
        List<CourseInfo> list = getCourseByTeacherId(id);
        List<CourseInfo> courseInfos = new ArrayList<>();
        for (CourseInfo c: list) {
            if (c.getName().contains(name)){
                courseInfos.add(c);
            }
        }
        return courseInfos;
    }

    @Override
    public List<PunishmentInfo> getPunishByStudentId(List<Integer> idList) {
        return teacherMapper.selectPunishByStudentId(idList);
    }

    @Override
    public List<PunishmentInfo> getPunishByStudentName(Integer id, String name) {
        List<Integer> idList = getStudentIdForClazz(id);
        List<PunishmentInfo> punish = getPunishByStudentId(idList);
        List<PunishmentInfo> list = new ArrayList<>();
        for (PunishmentInfo p: punish) {
            if (p.getUserName().contains(name)){
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public List<Award> getAwardByStudentId(List<Integer> idList) {
        return  teacherMapper.selectAwardByStudentId(idList);
    }

    @Override
    public List<Award> getAwardByStudentName(Integer id, String name) {
        List<Integer> idList = getStudentIdForClazz(id);
        List<Award> awards = getAwardByStudentId(idList);
        List<Award> list = new ArrayList<>();
        for (Award a: awards) {
            if (a.getUserName().contains(name)){
                list.add(a);
            }
        }
        return list;
    }

    @Override
    public List<Leave> getLeaveByClazzStudentId(List<Integer> idList) {
        return teacherMapper.selectLeaveByStudentIdForClazz(idList);
    }

    @Override
    public List<Leave> getLeaveByStudentName(Integer id, String name) {
        List<Integer> list = getStudentIdForClazz(id);
        List<Leave> leaves = getLeaveByClazzStudentId(list);
        List<Leave> leaveList = new ArrayList<>();
        for (Leave l: leaves) {
            if (l.getUserName().contains(name)){
                leaveList.add(l);
            }
        }
        return leaveList;
    }

    @Override
    public CourseInfo getCourseById(Integer id) {
        return teacherMapper.selectCourseById(id);
    }

    @Override
    public List<Clazz> getLikeName(String name) {
        return teacherMapper.selectLikeName(name);
    }

    @Override
    public List<Clazz> getClazzName() {
        return teacherMapper.selectClazzName();
    }

    @Override
    public List<Clazz> getStudentByTeacherId(Integer id) {
        return teacherMapper.selectStudentByTeacherId(id);
    }

    @Override
    public List<Clazz> getByTeacherId(Integer id) {
        return teacherMapper.selectByTeacherId(id);
    }

    @Override
    public List<Integer> getStudentIdForClazz(Integer teacherId) {
        List<Clazz> byTeacherId = getByTeacherId(teacherId);
        List<Integer> idList = new ArrayList<>();
        for (Clazz c:byTeacherId) {
            idList.add(c.getStudentId());
        }
        return idList;
    }

    @Override
    public List<Course> getCourseInfoForTeacher(List<Integer> idList) {
        return teacherMapper.selectCourseInfoForTeacher(idList);
    }

    @Override
    public List<Course> getCourseByStuNameAndCsName(String stuName, String csName, Integer teacherId) {
        List<Integer> idList = getStudentIdForClazz(teacherId);
        List<Course> courseList = getCourseInfoForTeacher(idList);
        List<Course> courses = new ArrayList<>();
        for (Course c: courseList){
            if (stuName.equals("无")){
                if (c.getCourseName().contains(csName)){
                    courses.add(c);
                }
            }else if (csName.equals("无")){
                if (c.getStudentName().contains(stuName)){
                    courses.add(c);
                }
            }else{
                if (c.getStudentName().contains(stuName) && c.getCourseName().contains(csName)){
                    courses.add(c);
                }
            }

        }
        return courses;
    }

    @Override
    public List<Object> getStudentInfo(Integer id) {
        List<Clazz> list = getStudentByTeacherId(id);
        List<Object> list2 = new ArrayList<>();
        for (Clazz clazz:list) {
            List<Student> list1 = clazz.getList();
            Student student = list1.get(0);
            student.setClazzName(clazz.getClazzName());
            list2.addAll(list1);
        }
        return list2;
    }

    @Override
    public List<Student> getStudentForName(Integer id, String studentName, String clazzName) {
        List<Object> studentInfo = getStudentInfo(id);
        List<Student> list = new ArrayList<>();
        for (Object o:studentInfo) {
            Student student = (Student) o;
            if (studentName.equals("")){
                if (student.getClazzName().equals(clazzName)){
                    list.add(student);
                }
            }else if (clazzName.equals("")) {
                if (student.getUsername().contains(studentName)){
                    list.add(student);
                }
            }else {
                if (student.getUsername().contains(studentName) && student.getClazzName().equals(clazzName)){
                    list.add(student);
                }
            }
        }
        return list;
    }
}
