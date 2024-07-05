package br.com.sp.Usuarios.handleException.body;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatusCode;

import jakarta.servlet.http.HttpServletRequest;

public class ResponseBody {
	private String titulo;
	private StringBuffer Url;
	private Integer status;
	private String recurso;
	private String dataRequisicao;
	private List<Campo> campos;

	public ResponseBody(HttpStatusCode status, List<Campo> campos) {
		this.titulo = "Campos inválidos, tente novamente";
		this.status = status.value();
		this.dataRequisicao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		this.campos = campos;
	}

	public ResponseBody(String message, int value, HttpServletRequest requestPath) {
		this.titulo = message;
		this.status = value;
		this.Url = requestPath.getRequestURL();
		this.recurso = requestPath.getRequestURI();
		this.dataRequisicao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public StringBuffer getUrl() {
		return Url;
	}

	public void setUrl(StringBuffer url) {
		Url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(String dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	
	

}
