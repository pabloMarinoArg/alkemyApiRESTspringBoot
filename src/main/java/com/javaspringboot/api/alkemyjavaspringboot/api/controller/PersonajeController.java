package com.javaspringboot.api.alkemyjavaspringboot.api.controller;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Personaje;
import com.javaspringboot.api.alkemyjavaspringboot.api.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters")
public class PersonajeController {


    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public List<Personaje> listarPersonajes(){
        return personajeService.listadoPersonajes();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Personaje character, BindingResult result){
        Personaje newCharacter = null;
        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newCharacter = personajeService.crearPersonaje(character);
        }catch (DataAccessException e){
            response.put("mensaje","Error when posting character to database");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","The character has been created OK");
        response.put("personaje",newCharacter);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);



    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@Valid @RequestBody Personaje character, BindingResult result,@PathVariable String id){
        Personaje personajeActual = personajeService.searchCharacterById(id);
        Personaje personajeModificado = null;
        Map<String,Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            personajeActual.setName(character.getName());
            personajeActual.setAge(character.getAge());
            personajeActual.setWeight(character.getWeight());
            personajeActual.setHistory(character.getHistory());
            personajeActual.setMovieList(character.getMovieList());
            personajeModificado = personajeService.crearPersonaje(personajeActual);
        }catch (DataAccessException e){
            response.put("mensaje","Error al modificar el personaje en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personajeActual == null){
            response.put("mensaje","El personaje ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }



        response.put("mensaje","El personaje se modifico con exito");
        response.put("personaje",personajeModificado);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Map<String,Object> response = new HashMap<>();


        try {
            Personaje personaje = personajeService.searchCharacterById(id);
            String  oldPictureName = personaje.getPicture();
            if(oldPictureName!=null && oldPictureName.length() >0){
                Path rutaFotoAnterior = Paths.get("uploads").resolve(oldPictureName).toAbsolutePath();
                File fileOldPicture = rutaFotoAnterior.toFile();
                if(fileOldPicture.exists() && fileOldPicture.canRead()){
                    fileOldPicture.delete();
                }
            }
            personajeService.eliminarPersonaje(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar personaje de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El personaje se ha eliminado con exito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPicture(@RequestParam("file")MultipartFile file, @RequestParam("id") String id){
        Map<String,Object> response = new HashMap<>();
        Personaje personaje  = personajeService.searchCharacterById(id);

        if(!file.isEmpty()){
            String fileName = UUID.randomUUID().toString() +"_"+ file.getOriginalFilename().replace(" ","");
            Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();

            try{
                Files.copy(file.getInputStream(), filePath);
            }catch (IOException e){
                e.printStackTrace();
                response.put("mensaje", "Error al subir la imagen del personaje");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            personaje.setPicture(fileName);

            personajeService.crearPersonaje(personaje);
            response.put("personaje", personaje);
            response.put("mensaje", "Has subido con exito la foto del personaje "+fileName);

        }

       /* String  oldPictureName = personaje.getPicture();
        if(oldPictureName!=null && oldPictureName.length() >0){
            Path rutaFotoAnterior = Paths.get("uploads").resolve(oldPictureName).toAbsolutePath();
            File fileOldPicture = rutaFotoAnterior.toFile();
            if(fileOldPicture.exists() && fileOldPicture.canRead()){
                fileOldPicture.delete();
            }
        }*/			

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }





    }











