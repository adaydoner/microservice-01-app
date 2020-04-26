# microservice-01-app


userratedmoviesservice's port = 8083
http://localhost:8083/ratingsdata/users/"+ userId

returns List<Rating> type , data spesified hardcoded.
Rating fields ;
	private String userId;
	private String movieId;
	private double rating;




movieinfoservice's port = 8082
http://localhost:8082/movies/"+ movieId

returns Movie type , data spesified hardcoded.
Movie fields;
	private int id;
	private String name;
	private String desc;



moviecatalogservice's port = 8081
http://localhost:8081/catalog/+ userId

returns Catalog type , data comes from other two web services.


Catalog fields ;
	private Movie theMovie;
	private double rating;
	


1 - Run three app
2 - Make request to moviecatalogservice "http://localhost:8081/catalog/1"

What happens behind the scenes;
1 - moviecatalogservice makes request "http://localhost:8083/ratingsdata/users/1" and retrieves user's ratings(userId,movieId,rating)
2 - moviecatalogservice makes another request "http://localhost:8082/movies/"+ movieId" , movieId will change for each user's rated movie's id and retrieve that movie's details
3 - Bind two response in one object called Catalog and returns it.






