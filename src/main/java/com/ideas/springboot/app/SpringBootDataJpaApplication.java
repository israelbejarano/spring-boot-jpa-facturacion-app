package com.ideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ideas.springboot.app.models.service.IUploadFileService;

/**
 * The Class SpringBootDataJpaApplication.
 */
@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {
	
	/** The upload file service. */
	@Autowired
	IUploadFileService uploadFileService;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	/**
	 * Run. Funcion que crea el direcotrio uploads al levantar la app.
	 * @author Israel Bejarano
	 *
	 * @param args the args
	 * @throws Exception the exception
	 */
	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
	}
}