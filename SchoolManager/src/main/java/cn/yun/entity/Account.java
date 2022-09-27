package cn.yun.entity;

import lombok.Data;

@Data
public class Account {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private byte sex;

    private String phone;

    private String birthday;

    private String nation;

    private String address;

    private String imgAddress;

    private Integer role;

    private Integer invitationCode;

    private String emailCode;

}
