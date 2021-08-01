package com.javaspringboot.api.alkemyjavaspringboot.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Pelicula;
import com.javaspringboot.api.alkemyjavaspringboot.api.repository.PeliculaRepository;
import com.javaspringboot.api.alkemyjavaspringboot.api.service.PeliculaService;



@RestController
@RequestMapping("/movies")
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Pelicula pelicula){
		Pelicula newMovie ;
		Map<String, Object> response = new HashMap<>();
		try {
			newMovie = peliculaService.crearPelicula(pelicula);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear pelicula en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("pelicula", newMovie);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Pelicula> lista = new ArrayList<>();
		Map<String,Object> response = new HashMap<>();
		
		try {
			lista = peliculaService.listaPelicula();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al listar las peliculas de la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("pelicula", lista);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable String id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			peliculaService.eliminarPelicula(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "No se ha podido eliminar la pelicula de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje","Se ha eliminado la pelicula con exito de la base de datos");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@RequestBody Pelicula pelicula, @PathVariable String id){
		Map<String,Object> response = new HashMap<>();
		Pelicula actual = peliculaService.searchMovieById(id);
		Pelicula modificada = pelicula;
						
		try {
			actual.setTitle(modificada.getTitle());
			actual.setCreationDate(pelicula.getCreationDate());
			actual.setCalification(pelicula.getCalification());
			actual.setGender(pelicula.getGender());
			actual.setCharacterList(pelicula.getCharacterList());
			actual.setPicture(pelicula.getPicture());
			peliculaService.crearPelicula(actual);
		} catch (DataAccessException e) {
			response.put("mensaje", "La pelicula no pudo ser modificada");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La pelicula se ha modificado con exito");
		response.put("pelicula", modificada);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	
}
