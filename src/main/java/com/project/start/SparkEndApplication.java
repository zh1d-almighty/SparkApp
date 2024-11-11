package com.project.start;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SparkEndApplication {

	public static void main(String[] args) {
		//System.setProperty("server.port","80");
		SpringApplication.run(SparkEndApplication.class, args);
		
	}

}
