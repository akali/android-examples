package com.example.aqali.asongoficeandfire;

import java.util.ArrayList;

/**
 * Created by aqali on 1/8/17.
 */
public class Book {
	private String name, publisher, country;
	private ArrayList<String> authors, characters;
	private int numberOfPages;

	public ArrayList<String> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<String> characters) {
		this.characters = characters;
	}

	public Book() {
		authors = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
}
