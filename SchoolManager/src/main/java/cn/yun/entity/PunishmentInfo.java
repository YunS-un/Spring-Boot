package cn.yun.entity;

import lombok.Data;

@Data
public class PunishmentInfo {

    private Integer id;
    private Integer punishmentId;
    private String info;
    private char status;

    //外链表
    private String punishName;
    private String punishTime;
    private String punishInfo;
    private String userName;


}
