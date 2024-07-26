package com.geo.consultatabelafipe.consultaTabelaFIPE;

import com.geo.consultatabelafipe.consultaTabelaFIPE.principal.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class ConsultaTabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaTabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main principal = new Main();
		principal.exibirMenu();
	}
}
