package br.com.raxinformatica.explorandomarte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "br.com.raxinformatica.explorandomarte.models")
@SpringBootApplication
public class ExplorandoMarteApplication {

    public static void main(String[] args) {
        System.out.println("Agora foi?");
        SpringApplication.run(ExplorandoMarteApplication.class, args);
    }


}
