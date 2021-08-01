package com.javaspringboot.api.alkemyjavaspringboot.api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;

    @NotEmpty
    private String title;

    
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Min(value=1)
    @Max(value=5)
    private Integer calification;

    private String picture;

    
    @OneToMany
    private List<Personaje> characterList;

    @ManyToOne
    private Genero gender;

    public Pelicula() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCalification() {
        return calification;
    }

    public void setCalification(Integer calification) {
        this.calification = calification;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Genero getGender() {
        return gender;
    }

    public void setGender(Genero gender) {
        this.gender = gender;
    }

    public List<Personaje> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<Personaje> characterList) {
        this.characterList = characterList;
    }
}
