package cn.yun.entity;

import lombok.Data;

import java.util.List;

@Data
public class Award {

    private Integer id;
    private Integer studentId;
    private Integer awardId;
    private String info;
    private char status;

    //外链表
    private String name;
    private String userName;
    private Integer money;
    private List<AwardInfo> awardInfos;

}
