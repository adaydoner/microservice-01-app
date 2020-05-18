package adaydoner.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import adaydoner.moviecatalogservice.model.Catalog;
import adaydoner.moviecatalogservice.model.Movie;
import adaydoner.moviecatalogservice.model.Rating;

@Service
public class MovieInfoService {
	
	@Autowired
	private WebClient webClient;
	
	@HystrixCommand(fallbackMethod="getFallbackCatalog")
	public Catalog getCatalog(Rating oneOfRatings) {
		Movie movie = webClient // using created web client 
				.get() // http method that we want to use
				.uri("http://movie-info-service/movies/"+ oneOfRatings.getMovieId()) // requested uri
				.retrieve()
				.bodyToMono(Movie.class) // mono mean response body eventually retrieved for spesified class.
				.block(); // wait until bodyToMono happens, we use it because we don't do reactive programming
		return new Catalog(movie, oneOfRatings.getRating());
	}
	public Catalog getFallbackCatalog(Rating oneOfRatings) {
		return new Catalog(new Movie(0, "fallback method of movie info", "dummy data"), oneOfRatings.getRating());
	}

}
