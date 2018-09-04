package com.example.aqali.mcauabnb;

/**
 * Created by aqali on 1/21/17.
 */

public class City {
	private String objectId;

	public City(String title, String image) {
		this.title = title;
		this.image = image;
	}

	private String title;
	private String image;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "City{" +
			"title='" + title + '\'' +
			", image='" + image + '\'' +
			'}';
	}
}
