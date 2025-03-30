package com.yorku.green_charge_auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GreenChargeAutoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GreenChargeAutoApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GreenChargeAutoApplication.class);
    }

}
