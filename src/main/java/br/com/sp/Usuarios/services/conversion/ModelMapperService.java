package br.com.sp.Usuarios.services.conversion;

import br.com.sp.UsuarioDTOS.input.InputUsuarioDTO;
import br.com.sp.UsuarioDTOS.output.OutputUsuarioDTO;
import br.com.sp.Usuarios.domain.usuario.Usuarios;

public interface ModelMapperService {

	OutputUsuarioDTO convertUserDTO(Usuarios usuario);

	Usuarios convertUserDTOtoUser(InputUsuarioDTO inputUsuarioDTO);

}
