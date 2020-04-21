package adaydoner.moviecatalogservice.model;

public class Movie {

	private int id;
	private String name;
	private String desc;

	public Movie() {

	}

	public Movie(int movieId, String movieName, String desc) {
		id = movieId;
		name = movieName;
		this.desc = desc;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * toString()
	 */

	@Override
	public String toString() {
		return "Movie [movieId=" + id + ", movieName=" + name + "]";
	}

	

}
