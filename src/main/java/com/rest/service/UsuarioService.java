package com.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.dao.IUsuarioDao;
import com.rest.model.Usuario;



@Service
public class UsuarioService implements IUsuarioService{
	private IUsuarioDao usuarioDao;

	public UsuarioService(IUsuarioDao usuarioDao) {

		this.usuarioDao = usuarioDao;
	}
	

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioDao.findAll();
	}
	
	@Override
	public List<Usuario> listarUsuarios(String correo) {
		if(correo != null) {
			return usuarioDao.findByCorreo(correo);
		}
		return usuarioDao.findAll();
	}

	@Override
	public Page<Usuario> listarPaginado(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

	@Override
	public Usuario buscarPorId(int id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public void eliminarUsuario(int id) {
		usuarioDao.deleteById(id);
	}

	@Override
	public  void registrarUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}





}
