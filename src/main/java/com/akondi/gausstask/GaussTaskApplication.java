package com.akondi.gausstask;

import com.akondi.webapplication.configuration.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class GaussTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaussTaskApplication.class, args);
    }
}
