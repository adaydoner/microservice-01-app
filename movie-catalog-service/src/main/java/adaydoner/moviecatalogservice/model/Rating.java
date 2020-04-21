package adaydoner.moviecatalogservice.model;

public class Rating {

	private String userId;
	private String movieId;
	private double rating;

	public Rating() {

	}

	public Rating(String userId, String movieId, double rating) {
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}

	/*
	 * getters and setters
	 */

	public String getMovieId() {
		return movieId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	/*
	 * toString() method
	 */
	@Override
	public String toString() {
		return "Rating [userId=" + userId + ", movieId=" + movieId + ", rating=" + rating + "]";
	}

}
