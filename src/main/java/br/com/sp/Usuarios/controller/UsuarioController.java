package br.com.sp.Usuarios.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sp.UsuarioDTOS.input.InputUsuarioDTO;
import br.com.sp.UsuarioDTOS.input.PutUsuarioDTO;
import br.com.sp.UsuarioDTOS.output.OutputUsuarioDTO;
import br.com.sp.Usuarios.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/listAll")
	public ResponseEntity<Page<OutputUsuarioDTO>> listAllEnabled(Pageable pageable) {
		Page<OutputUsuarioDTO> listAll = usuarioService.listAllEnabled(pageable);
		
		return ResponseEntity.ok(listAll);
	}
	
	@GetMapping("/listAllDisabled")
	public ResponseEntity<Page<OutputUsuarioDTO>> listAllDisabled(Pageable pageable){
		Page<OutputUsuarioDTO> listAll = usuarioService.listAllDisabled(pageable);
		
		return ResponseEntity.ok(listAll);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<OutputUsuarioDTO> findById(@PathVariable UUID id) {
		OutputUsuarioDTO usuarioDTO = usuarioService.findById(id);
		
		return ResponseEntity.ok(usuarioDTO);
	}
	
	@PostMapping("/save")
	public ResponseEntity<OutputUsuarioDTO> save(@Valid @RequestBody InputUsuarioDTO inputUsuarioDTO, UriComponentsBuilder uriBuilder) {
		OutputUsuarioDTO user = usuarioService.save(inputUsuarioDTO);
		URI uri = uriBuilder.path("/api/users/findOne/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<OutputUsuarioDTO> update(@PathVariable UUID id, @Valid @RequestBody PutUsuarioDTO inputUsuarioDTO) {
		OutputUsuarioDTO user = usuarioService.update(id, inputUsuarioDTO);
		
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id) {
		boolean deleted = usuarioService.delete(id);
		
		if (deleted)
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.notFound().build();
	}
}
