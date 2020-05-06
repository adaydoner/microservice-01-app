package adaydoner.moviecatalogservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import adaydoner.moviecatalogservice.model.Catalog;
import adaydoner.moviecatalogservice.model.Movie;
import adaydoner.moviecatalogservice.model.UserRatings;

@RestController
@RequestMapping("/catalog")
public class CatalogRestController {

	
	@Autowired
	private WebClient webClient;
	
	
	@RequestMapping("/{userId}")
	@HystrixCommand(fallbackMethod="getFallbackCatalogsByUserId")
	public List<Catalog> getCatalogsByUserId(@PathVariable("userId") int userId){
		
		UserRatings ratings = webClient
				.get()
				.uri("http://user-rated-movies-service/ratingsdata/users/"+ userId)
				.retrieve()
				.bodyToMono(UserRatings.class)
				.block();
		
		
		return ratings.getUserRatings().stream().map(oneOfRatings -> {
			Movie movie = webClient // using created web client 
					.get() // http method that we want to use
					.uri("http://movie-info-service/movies/"+ oneOfRatings.getMovieId()) // requested uri
					.retrieve()
					.bodyToMono(Movie.class) // mono mean response body eventually retrieved for spesified class.
					.block(); // wait until bodyToMono happens, we use it because we don't do reactive programming
			return new Catalog(movie, oneOfRatings.getRating());
			}).collect(Collectors.toList());
		
	}
	
	public List<Catalog> getFallbackCatalogsByUserId(@PathVariable("userId") int userId){
		Movie movie = new Movie(-1, "", "");
		return Arrays.asList(new Catalog(movie, 5));
	}
	

}
