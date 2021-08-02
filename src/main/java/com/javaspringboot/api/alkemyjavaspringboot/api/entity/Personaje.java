package com.javaspringboot.api.alkemyjavaspringboot.api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Min(value=1)
    @Column(nullable = false)
    private Integer age;


    @Column(nullable = false)
    private Double weight;

    @NotEmpty
    @Column(nullable = false)
    private String history;

    private String picture;


    @ManyToMany
    @JoinTable(name="CHARACTER_MOVIES",
    			joinColumns = @JoinColumn(name="CHARACTER_ID"),
    			inverseJoinColumns = @JoinColumn(name="MOVIE_ID"))
    
    private Set<Pelicula> movieList = new HashSet<>();

    public Personaje() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

	public Set<Pelicula> getMovieList() {
		return movieList;
	}

	public void setMovieList(Set<Pelicula> movieList) {
		this.movieList = movieList;
	}

  
}
