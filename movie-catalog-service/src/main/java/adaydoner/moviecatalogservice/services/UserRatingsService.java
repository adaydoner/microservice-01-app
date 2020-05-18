package adaydoner.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import adaydoner.moviecatalogservice.model.Rating;
import adaydoner.moviecatalogservice.model.UserRatings;

@Service
public class UserRatingsService {

	@Autowired
	private WebClient webClient;
	
	@HystrixCommand(fallbackMethod="getFallbackUserRating")
	public UserRatings getUserRating(int userId) {
		return webClient
				.get()
				.uri("http://user-rated-movies-service/ratingsdata/users/"+ userId)
				.retrieve()
				.bodyToMono(UserRatings.class)
				.block();
	}
	public UserRatings getFallbackUserRating(int userId) {
		UserRatings userRatings = new UserRatings();
		userRatings.setUserRatings(Arrays.asList(
				new Rating("0", "fallback method of user rating", 0)
				));
		
		return userRatings;
	}
}
