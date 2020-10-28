package com.akondi.webapplication;

import com.akondi.webapplication.configuration.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.akondi.*"})
@Import(SwaggerConfiguration.class)
public class GaussTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaussTaskApplication.class, args);
    }
}
