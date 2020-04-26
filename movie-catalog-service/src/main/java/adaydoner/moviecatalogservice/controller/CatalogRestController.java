package adaydoner.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import adaydoner.moviecatalogservice.model.Catalog;
import adaydoner.moviecatalogservice.model.Movie;
import adaydoner.moviecatalogservice.model.UserRatings;

@RestController
@RequestMapping("/catalog")
public class CatalogRestController {
	
/*	@Autowired
	private RestTemplate restTemplate;*/
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	@RequestMapping("/{userId}")
	public List<Catalog> getCatalogsByUserId(@PathVariable("userId") int userId){
		
		UserRatings ratings = webClientBuilder.build()
				.get()
				.uri("http://user-rated-movies-service/ratingsdata/users/"+ userId)
				.retrieve()
				.bodyToMono(UserRatings.class)
				.block();
		
		
		return ratings.getUserRatings().stream().map(oneOfRatings -> {
			//Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+ oneOfRatings.getMovieId(),Movie.class);
			Movie movie = webClientBuilder.build() // creating web client 
					.get() // http method that we want to use
					.uri("http://movie-info-service/movies/"+ oneOfRatings.getMovieId()) // requested uri
					.retrieve()
					.bodyToMono(Movie.class) // mono mean response body eventually retrieved for spesified class.
					.block(); // wait until bodyToMono happens, we use it because we don't do reactive programming
			return new Catalog(movie, oneOfRatings.getRating());
			}).collect(Collectors.toList());
		
	}

}
