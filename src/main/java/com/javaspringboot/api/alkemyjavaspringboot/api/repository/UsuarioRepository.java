package com.javaspringboot.api.alkemyjavaspringboot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);
	/*public Usuario findByUsernameAndEmail(String)*/
	
}
