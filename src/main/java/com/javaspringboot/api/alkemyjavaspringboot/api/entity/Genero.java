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

    @OneToMany
    private List<Pelicula> movieList;

}
