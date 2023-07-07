package com.rest.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Usuario;

public interface IUsuarioDao  extends JpaRepository<Usuario, Integer>{
	public List<Usuario> findByCorreo(String correo);
	

}
