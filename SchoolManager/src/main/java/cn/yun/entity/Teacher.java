package cn.yun.entity;

import lombok.Data;

import java.util.List;

@Data
public class Teacher{

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

    public Teacher(){}

    public Teacher(String email,String password, Integer role) {
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Teacher(String email, Integer role) {
        this.email = email;
        this.role = role;
    }

    public Teacher(Integer id, String username, String email, byte sex, String phone, String birthday, String nation, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.birthday = birthday;
        this.nation = nation;
        this.address = address;
    }
}
