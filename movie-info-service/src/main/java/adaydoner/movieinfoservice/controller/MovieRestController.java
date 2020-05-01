package adaydoner.movieinfoservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import adaydoner.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieRestController {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	
	@RequestMapping("/{movieId}")
	public Movie getMovieById(@PathVariable("movieId") int movieId) {
		
		Movie movieDetails = webClientBuilder
				.build()
				.get()
				.uri("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey)
				.retrieve()
				.bodyToMono(Movie.class).block();
		

		return new Movie(movieId, movieDetails.getTitle(), movieDetails.getOverview());

	}
}
