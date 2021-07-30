package com.javaspringboot.api.alkemyjavaspringboot.api.repository;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository <Personaje, String> {

    @Modifying
    @Query("UPDATE Personaje p SET p.name = :name, p.age = :age, p.weight = :weight, p.history =:history WHERE p.id = :id")
    void modificar(@Param("id") String id, @Param("name") String name, @Param("age") Integer age, @Param("weight") Double weight,@Param("history") String history);

    List<Personaje> findPersonajeByName(String name);
    List<Personaje> findPersonajeByNameAndAge(String name, Integer age);
    List<Personaje> findPersonajeByNameAndWeight(String name, Double weight);
    //List<Personaje> findPersonajeByMovieList(String name);

}
