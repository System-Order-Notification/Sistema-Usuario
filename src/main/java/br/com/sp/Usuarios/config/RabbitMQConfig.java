package br.com.sp.Usuarios.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {
	
	/*
	 * Essa classe é responsável por realizar as conversões do payload da mensagem, de string para object
	 */
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		return new Jackson2JsonMessageConverter(mapper);
	}

}
