package cn.yun.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Course {

    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Integer score;

    //外链数据
    private String courseName;
    private String studentName;
    private List<CourseInfo> courseList;

}
