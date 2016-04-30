package com.mdt.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

////Build a jar package
//@SpringBootApplication
//public class Application {
//    public static void main(String[] args) throws Exception{
//        System.out.println("Welcome QrCode");
//        SpringApplication.run(Application.class, args);
//    }
//}


//Build a war package
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}