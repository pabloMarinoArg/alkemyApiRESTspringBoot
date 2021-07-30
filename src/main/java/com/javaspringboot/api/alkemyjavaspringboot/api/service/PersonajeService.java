package com.javaspringboot.api.alkemyjavaspringboot.api.service;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Personaje;
import com.javaspringboot.api.alkemyjavaspringboot.api.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository pr;

    @Transactional
    public Personaje crearPersonaje(Personaje character ){

        return pr.save(character);

    }

    @Transactional
    public void modificarPersonaje(String id, String name, Integer age, Double weight, String history ){
        pr.modificar(id, name, age, weight, history);

    }

    @Transactional
    public void eliminarPersonaje(String id){

        pr.deleteById(id);

    }

    @Transactional(readOnly = true)
    public List<Personaje> listadoPersonajes (){

        return pr.findAll();

    }

    @Transactional(readOnly = true)
    public Personaje searchCharacterById(String id){

        Optional<Personaje> personajeOptional = pr.findById(id);

        return personajeOptional.orElse(null);

    }

    @Transactional(readOnly = true)
    public List<Personaje> listarPersonajePorNombre(String name){
        return pr.findPersonajeByName(name);
    }

    @Transactional(readOnly = true)
    public List<Personaje> listarPersonajePorNombreYEdad(String name, Integer age){
        return pr.findPersonajeByNameAndAge(name,age);
    }

    @Transactional(readOnly = true)
    public List<Personaje> listarPersonajePorNombreYPeso(String name, Double weight){
        return pr.findPersonajeByNameAndWeight(name, weight);
    }



}
