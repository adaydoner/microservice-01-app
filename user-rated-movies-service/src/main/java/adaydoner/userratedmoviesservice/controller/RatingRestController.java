package adaydoner.userratedmoviesservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adaydoner.userratedmoviesservice.model.Rating;
import adaydoner.userratedmoviesservice.model.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingRestController {

	@RequestMapping("/users/{userId}")
	public UserRatings getRatingByUserId(@PathVariable("userId") String userId){
		List<Rating> ratings = null;
		
		switch (userId) {
		case "1":
			ratings = Arrays.asList(
					new Rating(userId,"2", 8.8),
					new Rating(userId,"3", 9.3)
					);
			break;
		case "2":
			ratings = Arrays.asList(
					new Rating(userId,"1", 2),
					new Rating(userId,"3", 2)
					);
			break;
		case "3":
			ratings = Arrays.asList(
					new Rating(userId,"1", 3),
					new Rating(userId,"2", 3)
					);
			break;
		default:
			break;
		}
		
		return new UserRatings(ratings);
	}
}
