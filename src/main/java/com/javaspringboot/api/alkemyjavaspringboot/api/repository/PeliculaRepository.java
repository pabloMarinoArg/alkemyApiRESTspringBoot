package com.javaspringboot.api.alkemyjavaspringboot.api.repository;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,String> {
}
