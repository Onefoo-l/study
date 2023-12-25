package com.onefool;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Onefool
 * @Date 2023/12/24 9:06
 */
@SpringBootApplication
@MapperScan("com.onefool.*.mapper")
public class SpringStart {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringStart.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringStart.class,args);
        LOGGER.info("项目启动成功!!");
    }
}
