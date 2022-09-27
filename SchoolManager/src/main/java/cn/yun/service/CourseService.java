package cn.yun.service;

import cn.yun.entity.CourseInfo;

import java.util.HashMap;
import java.util.List;

public interface CourseService {

    List<HashMap> getAllScores(String email);

    List<HashMap> selectScores(String email,String courseName);

    List<CourseInfo> getCourseInfo(String studentEmail);

    List<CourseInfo> selectCourse(String studentEmail,String courseName);

}
