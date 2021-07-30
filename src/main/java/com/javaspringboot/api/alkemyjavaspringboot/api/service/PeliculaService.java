package com.javaspringboot.api.alkemyjavaspringboot.api.service;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Pelicula;
import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Personaje;
import com.javaspringboot.api.alkemyjavaspringboot.api.repository.GeneroRepository;
import com.javaspringboot.api.alkemyjavaspringboot.api.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;


    @Transactional
    public Pelicula crearPelicula(Pelicula movie){

        return peliculaRepository.save(movie);

    }

    @Transactional
    public void eliminarPelicula(String id){
        peliculaRepository.deleteById(id);

    }

    @Transactional(readOnly = true)
    public List<Pelicula> listaPelicula(){
        return peliculaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pelicula searchMovieById(String id){

        return peliculaRepository.findById(id).orElse(null);
    }

}
