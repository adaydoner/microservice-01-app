package adaydoner.movieinfoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adaydoner.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieRestController {

	@RequestMapping("/{movieId}")
	public Movie getMovieById(@PathVariable("movieId") int movieId){
		Movie theMovie = null;
		switch (movieId) {
		case 1:
			theMovie = new Movie(1, "Lord Of The Rings","Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring");
			break;
		case 2:
			theMovie = new Movie(2, "Forrest Gump","The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate and other historical events unfold through the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.");
			break;
		case 3:
			theMovie = new Movie(3, "The Shawshank Redemption","Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.");
			break;
		default:
			break;
		}

		return theMovie;
		
	}
}
