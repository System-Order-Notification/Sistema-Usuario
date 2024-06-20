package br.com.sp.Usuarios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {
	
	@GetMapping("/listAll")
	public String listAll() {
		return "Teste";
	}
}
