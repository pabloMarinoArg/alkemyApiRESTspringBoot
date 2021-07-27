package com.javaspringboot.api.alkemyjavaspringboot.api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private Integer age;

    @NotEmpty
    @Column(nullable = false)
    private Double weight;

    @NotEmpty
    @Column(nullable = false)
    private String history;

    private String picture;

    @Column(nullable = false)
    @OneToMany(mappedBy = "character")
    private List<Pelicula> movieList;

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

    public List<Pelicula> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Pelicula> movieList) {
        this.movieList = movieList;
    }
}
