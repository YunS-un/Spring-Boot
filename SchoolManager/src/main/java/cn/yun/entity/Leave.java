package cn.yun.entity;

import lombok.Data;

@Data
public class Leave {

    private Integer id;

    private Integer studentId;

    private String info;

    private String departure;

    private String destination;

    private String returnTime;

    private char status;

    private String desc;

    //外联表
    private String userName;

}
