package de.berlin.sudokuBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SudokuBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuBackendApplication.class, args);
	}

}
