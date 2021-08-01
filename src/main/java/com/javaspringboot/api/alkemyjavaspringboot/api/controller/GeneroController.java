package com.javaspringboot.api.alkemyjavaspringboot.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Genero;
import com.javaspringboot.api.alkemyjavaspringboot.api.service.GeneroService;

@RestController
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private GeneroService generoService;
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Genero genero){
		Map<String,Object> response = new HashMap<>();
		Genero genNew = genero;
		try {
			generoService.createGender(genero);
		} catch (DataAccessException e) {
			response.put("mensaje", "No se pudo guardar el genero en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Se ha guardado exitosamente el genero");
		response.put("genero", genNew );
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminar(@PathVariable String id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			generoService.deleteGenderById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "No se pudo borrar el genero en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Se ha eliminado exitosamente el genero");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> modificar(){
		Map<String,Object> response = new HashMap<>();
		
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		Map<String,Object> response = new HashMap<>();
		
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
}
