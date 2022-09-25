package cn.yun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan( value = "cn.yun.mapper")
public class SchoolManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagerApplication.class, args);
    }

}
