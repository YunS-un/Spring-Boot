package cn.yun.entity;

import lombok.Data;

@Data
public class Admin {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private Integer role;

    public Admin(String email, String password, Integer role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Admin(String email, Integer role) {
        this.email = email;
        this.role = role;
    }
}
