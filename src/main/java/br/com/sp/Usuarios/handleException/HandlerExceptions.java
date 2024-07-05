package br.com.sp.Usuarios.handleException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sp.Usuarios.handleException.body.Campo;
import br.com.sp.Usuarios.handleException.body.ResponseBody;
import br.com.sp.Usuarios.services.exceptions.UsuarioInactivationException;
import br.com.sp.Usuarios.services.exceptions.UsuarioNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<Campo> listaDeErros = new ArrayList<>();
		List<FieldError> todosOsErros = ex.getBindingResult().getFieldErrors();
		
		todosOsErros.forEach(error -> {
			String nome = error.getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			listaDeErros.add(new Campo(nome, mensagem));
		});
		
		ResponseBody respostaException = new ResponseBody(status, listaDeErros);
		
		return handleExceptionInternal(ex, respostaException, headers, status, request);
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ResponseBody> usuarioNotFoundException(UsuarioNotFoundException ex, HttpServletRequest requestPath) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ResponseBody exception = new ResponseBody(ex.getMessage(), status.value(), requestPath);
		
		return ResponseEntity.status(status).body(exception);
	}
	
	@ExceptionHandler(UsuarioInactivationException.class)
	public ResponseEntity<ResponseBody> usuarioInactivationException(UsuarioInactivationException ex, HttpServletRequest requestPath) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ResponseBody exception = new ResponseBody(ex.getMessage(), status.value(), requestPath);
		
		return ResponseEntity.status(status).body(exception);
	}
	
}
