package com.Landajo.DesafioFinal;

import com.Landajo.DesafioFinal.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//EJEMPLO DE INSERT CON CODIGO JAVA

//		Client ramiro = new Client();
//		ramiro.setName("Rodrigo");
//		ramiro.setLastname("Juarez");
//		ramiro.setDocnumber("55555555");
//		this.clientRepository.save(ramiro);
	}

}
