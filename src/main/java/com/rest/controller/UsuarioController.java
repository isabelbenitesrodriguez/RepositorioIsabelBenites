package com.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rest.model.Usuario;
import com.rest.service.IUsuarioService;


@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/listar")
	public String index(Model model, @RequestParam(required = false, defaultValue = "0")Integer page) {
		Page<Usuario> pg= usuarioService.listarPaginado(PageRequest.of(page, 5));
		model.addAttribute("usuario", pg);
		model.addAttribute("paginacion", "/listar");
		return "listar";
	}
	
	@GetMapping("/nuevo")
	public String registrarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/registrar";
	}
	
	@PostMapping("/guardar")
	public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
		usuarioService.registrarUsuario(usuario);
		return "redirect:/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable("id") int id) {
		usuarioService.eliminarUsuario(id);
		System.out.println("Usuario eliminado correctamente");
		return "redirect:/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String formularioEditar(@PathVariable("id") int id,Model model) {
		model.addAttribute("usuario", usuarioService.buscarPorId(id));
		return "/editar";
	}
	
	@PostMapping("/actualizar/{id}")
	public String actualizar(@PathVariable("id") int id, @ModelAttribute("usuario")Usuario usuario) {
		Usuario usuExistente= usuarioService.buscarPorId(id);
		usuExistente.setId(id);
		usuExistente.setCorreo(usuario.getCorreo());
		usuExistente.setPassword(usuario.getPassword());

		
		usuarioService.actualizarUsuario(usuExistente);			
		return "redirect:/listar";
	}
}
