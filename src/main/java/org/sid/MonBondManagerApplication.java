package org.sid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class MonBondManagerApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(MonBondManagerApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		//Candidat c1=candidatRepository.save(new Candidat("ABOU ABBAS", "Cécile", "Paris", "06 42 60 00 39", "maher@vizeo-technologies.com", "stagiaire"));
		//Candidat c2=candidatRepository.save(new Candidat("ZIDANE", "Zinedine", "Madrid", "06 55 56 54 54", "zidane@zizou.com", "A contacter"));
		
	}
}
