package cn.yun.entity;

import lombok.Data;

@Data
public class CourseInfo {

    private Integer id;
    private String name;
    private String courseTime;
    private String courseAddress;
    private Integer teacherId;

}
