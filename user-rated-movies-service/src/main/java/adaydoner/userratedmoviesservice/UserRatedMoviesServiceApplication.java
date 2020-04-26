package adaydoner.userratedmoviesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserRatedMoviesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRatedMoviesServiceApplication.class, args);
	}

}
