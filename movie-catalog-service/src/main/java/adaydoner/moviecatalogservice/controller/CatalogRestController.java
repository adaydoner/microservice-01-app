package adaydoner.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import adaydoner.moviecatalogservice.model.Catalog;
import adaydoner.moviecatalogservice.model.UserRatings;
import adaydoner.moviecatalogservice.services.MovieInfoService;
import adaydoner.moviecatalogservice.services.UserRatingsService;

@RestController
@RequestMapping("/catalog")
public class CatalogRestController {

	
	@Autowired
	MovieInfoService movieInfo;
	
	@Autowired
	UserRatingsService userRatingInfo;
	
	
	@RequestMapping("/{userId}")
	public List<Catalog> getCatalogsByUserId(@PathVariable("userId") int userId){
		
		UserRatings ratings = userRatingInfo.getUserRating(userId);
		
		
		return ratings.getUserRatings().stream().map(oneOfRatings -> {
			return movieInfo.getCatalog(oneOfRatings);
			}).collect(Collectors.toList());
		
	}
}
