package cn.yun.controller.util;

import lombok.Data;

@Data
public class R {

    private Boolean flag;
    private Object data;
    private String msg;

    public R(Boolean flag) {
        this.flag = flag;
    }

    public R(Object data) {
        this.data = data;
    }

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag, Object data, String msg) {
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }
}
