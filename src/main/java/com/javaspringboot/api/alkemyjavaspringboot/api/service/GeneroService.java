package com.javaspringboot.api.alkemyjavaspringboot.api.service;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Genero;
import com.javaspringboot.api.alkemyjavaspringboot.api.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Transactional
    public Genero createGender(Genero gender){
        return generoRepository.save(gender);
    }

    @Transactional
    public void deleteGenderById(String id){
        generoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Genero> genderList(){
        return generoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Genero searchGenderById(String id){
        Optional<Genero> genderOptional = generoRepository.findById(id);

        return genderOptional.orElse(null);

    }
}
