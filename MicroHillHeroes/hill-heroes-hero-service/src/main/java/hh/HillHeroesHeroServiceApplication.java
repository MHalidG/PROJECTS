package hh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class HillHeroesHeroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HillHeroesHeroServiceApplication.class, args);
	}

}
