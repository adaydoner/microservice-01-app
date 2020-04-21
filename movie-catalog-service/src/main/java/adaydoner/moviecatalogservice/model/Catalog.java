package adaydoner.moviecatalogservice.model;

public class Catalog {

	private Movie theMovie;
	private double rating;
	
	
	public Catalog(){
		
	}
	
	public Catalog(Movie theMovie, double rating) {
		super();
		this.theMovie = theMovie;
		this.rating = rating;
	}
	
	/*
	 * getters and setters
	 */

	public double getRating() {
		return rating;
	}

	public Movie getTheMovie() {
		return theMovie;
	}

	public void setTheMovie(Movie theMovie) {
		this.theMovie = theMovie;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	
	/**
	 * toString()
	 */

	@Override
	public String toString() {
		return "Catalog [theMovie=" + theMovie + ", rating=" + rating + "]";
	}
}
