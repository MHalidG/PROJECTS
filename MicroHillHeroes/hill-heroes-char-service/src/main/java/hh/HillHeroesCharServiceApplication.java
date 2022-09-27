package hh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class HillHeroesCharServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HillHeroesCharServiceApplication.class, args);
	}

}
