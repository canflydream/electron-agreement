package org.cfd.electron_agreement.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminApp {
    public static void main(String[] args) {
        System.setProperty("tlPath","E:/template/upload/test/html/");
        SpringApplication.run(AdminApp.class,args);
    }
}
