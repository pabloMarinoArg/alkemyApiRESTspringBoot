package com.javaspringboot.api.alkemyjavaspringboot.api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Genero {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String picture;

    @OneToMany(mappedBy = "gender")
    private List<Pelicula> movieList;

	public Genero() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Pelicula> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Pelicula> movieList) {
		this.movieList = movieList;
	}
    
	
    

}
