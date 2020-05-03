package adaydoner.movieinfoservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import adaydoner.movieinfoservice.model.Movie;
import ch.qos.logback.core.util.Duration;

@RestController
@RequestMapping("/movies")
public class MovieRestController {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private WebClient webClient;

	
	@RequestMapping("/{movieId}")
	public Movie getMovieById(@PathVariable("movieId") int movieId) {
		
		Movie movieDetails = webClient
				.get()
				.uri("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey)
				.retrieve()
				.bodyToMono(Movie.class)
				.block();
		

		return new Movie(movieId, movieDetails.getTitle(), movieDetails.getOverview());

	}
}
