package adaydoner.movieinfoservice.model;

public class Movie {

	private int id;
	private String title;
	private String overview;

	
	public Movie() {

	}

	public Movie(int id, String title, String overview) {
		this.id = id;
		this.title = title;
		this.overview = overview;
	}


	/**
	 * getters and setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}


	/**
	 * toString()
	 */

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", overview=" + overview + "]";
	}
	
	

}
