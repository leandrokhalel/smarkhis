package br.com.leandrokhalel.smarkhis;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Smarkhis API", version = "1.0", description = "API for Smarkhis"))
public class SmarkhisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarkhisApplication.class, args);
    }

}
