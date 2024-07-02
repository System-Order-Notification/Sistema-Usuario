package br.com.sp.Usuarios.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sp.UsuarioDTOS.input.InputUsuarioDTO;
import br.com.sp.UsuarioDTOS.input.PutUsuarioDTO;
import br.com.sp.UsuarioDTOS.output.OutputUsuarioDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

	Page<OutputUsuarioDTO> listAllEnabled(Pageable pageable);

	OutputUsuarioDTO findById(UUID id);

	boolean delete(UUID id);

	OutputUsuarioDTO save(InputUsuarioDTO inputUsuarioDTO);

	OutputUsuarioDTO update(UUID id, PutUsuarioDTO inputUsuarioDTO);

	Page<OutputUsuarioDTO> listAllDisabled(Pageable pageable);

}
