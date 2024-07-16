package br.com.sp.Usuarios.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import br.com.sp.Usuarios.domain.usuario.Usuarios;
import br.com.sp.emailDTO.output.EmailRecordDTO;

@Component
public class UserEmailProducer {
	
	private final RabbitTemplate rabbitTemplate;
	
	public UserEmailProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(Usuarios usuario) {
		EmailRecordDTO emailRecordDTO =
				new EmailRecordDTO(usuario.getId(), usuario.getEmail(), "Finalizamos seu Cadastro!", "Obrigado pelo seu cadastro e sua preferência");
		
		rabbitTemplate.convertAndSend("PostUsuarioDirect", "cadastro.usuario", emailRecordDTO);
	}
}
