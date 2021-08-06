package com.javaspringboot.api.alkemyjavaspringboot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaspringboot.api.alkemyjavaspringboot.api.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
