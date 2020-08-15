package org.cfd.electron_agreement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.setProperty("tlPath", "E:/template/upload/test/html/");
        SpringApplication.run(App.class, args);
    }
}
