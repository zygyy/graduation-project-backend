package com.zy;

import com.zy.util.ASEEncrypt;
import com.zy.util.JwtUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@MapperScan(value = "com.zy.mapper")
@SpringBootApplication
public class GraduationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationProjectApplication.class, args);
    }

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils();
    }

    @Bean
    public ASEEncrypt aSEEncrypt(){
        return new ASEEncrypt();
    }
}
