package com.TodoBANC.PruerbaFinalPOO;

import Vista.EstiloApexBank;
import Vista.VistaPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.TodoBANC.PruerbaFinalPOO",
        "Vista",
        "Controller",
        "Service",
        "Repository",
        "Modelo",
        "DTO",
        "Mappers"
})
@EnableJpaRepositories(basePackages = "Repository")
@EntityScan(basePackages = "Modelo")
public class PruerbaFinalPOOApplication implements CommandLineRunner {

    @Autowired
    private VistaPrincipal vistaPrincipal;

    public static void main(String[] args) {
        SpringApplication.run(PruerbaFinalPOOApplication.class, args);
    }

    @Override
    public void run(String... args) {
        EstiloApexBank.aplicarTema();
        vistaPrincipal.setVisible(true);
    }
}