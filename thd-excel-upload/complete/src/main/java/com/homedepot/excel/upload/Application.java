package com.homedepot.excel.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({com.homedepot.excel.upload.SwaggerConfiguration.class})
@ComponentScan("com.homedepot.excel.upload")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
