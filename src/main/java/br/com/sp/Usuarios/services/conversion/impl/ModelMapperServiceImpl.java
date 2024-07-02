package br.com.sp.Usuarios.services.conversion.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.sp.UsuarioDTOS.input.InputUsuarioDTO;
import br.com.sp.UsuarioDTOS.output.OutputUsuarioDTO;
import br.com.sp.Usuarios.domain.usuario.Usuarios;
import br.com.sp.Usuarios.services.conversion.ModelMapperService;

@Service
public class ModelMapperServiceImpl implements ModelMapperService{
	
	private final ModelMapper modelMapper;
	
	public ModelMapperServiceImpl(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	@Override
	public OutputUsuarioDTO convertUserDTO(Usuarios usuario) {
		return modelMapper.map(usuario, OutputUsuarioDTO.class);
		
	}
	
	@Override
	public Usuarios convertUserDTOtoUser(InputUsuarioDTO inputUsuarioDTO) {
		return modelMapper.map(inputUsuarioDTO, Usuarios.class);
	}

}
