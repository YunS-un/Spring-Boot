package cn.yun.entity;

import lombok.Data;

import java.util.List;

@Data
public class Punishment {

    private Integer id;
    private Integer studentId;
    private String name;
    private String punishmentTime;
    private String info;
    private List<PunishmentInfo> list;
}
