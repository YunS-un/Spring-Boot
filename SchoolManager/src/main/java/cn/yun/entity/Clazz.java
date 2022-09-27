package cn.yun.entity;

import lombok.Data;

import java.util.List;

@Data
public class Clazz {

    private Integer id;
    private String clazzName;
    private String info;
    private Integer studentId;
    private Integer teacherId;
    private List<Student> list;
}
