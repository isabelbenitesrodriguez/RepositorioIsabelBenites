package com.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rest.model.Usuario;

public interface IUsuarioService {
	public List<Usuario> listarUsuarios(String correo);
	public Page<Usuario> listarPaginado(Pageable pageable);
	void registrarUsuario(Usuario usuario);
	public Usuario buscarPorId(int id);
	public Usuario actualizarUsuario(Usuario usuario);
	public void eliminarUsuario(int id);
	public List<Usuario> listarUsuarios();
	
	
}
