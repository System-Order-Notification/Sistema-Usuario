package br.com.sp.Usuarios.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sp.UsuarioDTOS.input.InputUsuarioDTO;
import br.com.sp.UsuarioDTOS.input.PutUsuarioDTO;
import br.com.sp.UsuarioDTOS.output.OutputUsuarioDTO;
import br.com.sp.Usuarios.domain.usuario.Usuarios;
import br.com.sp.Usuarios.producers.UserEmailProducer;
import br.com.sp.Usuarios.repositories.UserRepository;
import br.com.sp.Usuarios.services.UsuarioService;
import br.com.sp.Usuarios.services.conversion.ModelMapperService;
import br.com.sp.Usuarios.services.exceptions.UsuarioInactivationException;
import br.com.sp.Usuarios.services.exceptions.UsuarioNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private final UserRepository userRepository;
	private final ModelMapperService modelMapperService;
	private final UserEmailProducer userEmailProducer;
	
	public UsuarioServiceImpl(UserRepository userRepository, ModelMapperService modelMapperService, UserEmailProducer userEmailProducer) {
		super();
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
		this.userEmailProducer = userEmailProducer;
	}

	@Override
	public Page<OutputUsuarioDTO> listAllEnabled(Pageable pageable) {
		Page<OutputUsuarioDTO> list = userRepository.findByEnabledTrue(pageable).map(usuario -> modelMapperService.convertUserDTO(usuario));
		return list;
	}
	
	@Override
	public Page<OutputUsuarioDTO> listAllDisabled(Pageable pageable) {
		Page<OutputUsuarioDTO> list = userRepository.findByEnabledFalse(pageable).map(usuario -> modelMapperService.convertUserDTO(usuario));
		return list;
	}

	@Override
	public OutputUsuarioDTO findById(UUID id) {
		Optional<OutputUsuarioDTO> optionalDTO = userRepository.findById(id).map(usuario -> modelMapperService.convertUserDTO(usuario));
		
		if (optionalDTO.isEmpty())
			throw new UsuarioNotFoundException("Usuário não encontrado");
		
		return optionalDTO.get();
	}
	
	@Transactional
	@Override
	public boolean delete(UUID id) {
		Optional<Usuarios> usuario = userRepository.findById(id);
		
		if (usuario.isEmpty()) {
			return false;
		}
		
		if (usuario.isPresent() && usuario.get().getEnabled() == false) {
			throw new UsuarioInactivationException("Esse usuário já foi inativado");
		}
		
		Usuarios user = usuario.get();
		user.setEnabled(false);
		userRepository.save(user);
		
		return true;
		
	}
	
	@Transactional
	@Override
	public OutputUsuarioDTO save(InputUsuarioDTO inputUsuarioDTO) {
		Usuarios usuario = modelMapperService.convertUserDTOtoUser(inputUsuarioDTO);
		userRepository.save(usuario);
		userEmailProducer.sendMessage(usuario);
		
		OutputUsuarioDTO userDTO = modelMapperService.convertUserDTO(usuario);
		return userDTO;
	}
	
	@Transactional
	@Override
	public OutputUsuarioDTO update(UUID id, PutUsuarioDTO inputUsuarioDTO) {
		Usuarios usuario = userRepository.findById(id)
				.orElseThrow();
		
		BeanUtils.copyProperties(inputUsuarioDTO, usuario);
		usuario.setDataAlteracao(LocalDateTime.now());
		userRepository.save(usuario);
		
		return modelMapperService.convertUserDTO(usuario);
	}

}
