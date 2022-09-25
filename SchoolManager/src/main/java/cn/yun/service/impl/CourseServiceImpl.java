package cn.yun.service.impl;

import cn.yun.entity.Course;
import cn.yun.entity.CourseInfo;
import cn.yun.service.CourseService;
import cn.yun.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private StudentService studentService;

    //获取成绩信息
    @Override
    public List<HashMap> getAllScores(String email) {
        List<HashMap> list1 = new ArrayList<>();
        List<Course> list = studentService.getByCourse(email).getList();
        for (Course course:list) {
            HashMap map = new HashMap();
            List<CourseInfo> courseList = course.getCourseList();
            CourseInfo courseInfo = courseList.get(0);
            map.put("id",courseInfo.getId());
            map.put("name",courseInfo.getName());
            map.put("score",course.getScore());
            list1.add(map);
        }
        return list1;
    }

    @Override
    public List<HashMap> selectScores(String email, String courseName) {
        List<HashMap> list = getAllScores(email);
        List<HashMap> scores = new ArrayList<>();
        for (HashMap map : list) {
            if (map.get("name").toString().contains(courseName)){
                scores.add(map);
            }
        }
        return scores;
    }

    //获取课程信息
    @Override
    public List<CourseInfo> getCourseInfo(String studentEmail) {
        List<CourseInfo> list1 = new ArrayList<>();
        List<Course> list = studentService.getByCourse(studentEmail).getList();
        for (Course course:list) {
            list1.addAll(course.getCourseList());
        }
        return list1;
    }

    //查询课程
    @Override
    public List<CourseInfo> selectCourse(String studentEmail,String courseName) {
        List<CourseInfo> list = getCourseInfo(studentEmail);
        List<CourseInfo> list1 = new ArrayList<>();
        for (CourseInfo courseInfo: list) {
            if (courseInfo.getName().contains(courseName)){
                list1.add(courseInfo);
            }
        }
        return list1;
    }
}
