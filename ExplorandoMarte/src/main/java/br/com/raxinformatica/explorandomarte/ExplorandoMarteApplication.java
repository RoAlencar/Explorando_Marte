package br.com.raxinformatica.explorandomarte;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExplorandoMarteApplication {

    public static void main(String[] args) {
        System.out.println("Agora foi?");
        SpringApplication.run(ExplorandoMarteApplication.class, args);
    }


}
