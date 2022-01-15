package com.algaworks.APIRestAlgaLogistics.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.APIRestAlgaLogistics.domain.exception.EntidadeNaoEncontradaExpection;
import com.algaworks.APIRestAlgaLogistics.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		
		String stringErro = "Um ou mais campos estão inválidos. "
				+ "Faça o preenchimento correto "
				+ "e tente novamente.";
		
		List<Problema.Campo> campos = new ArrayList<>();
		
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Problema.Campo(nome, mensagem));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(stringErro);
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaExpection.class)
	public ResponseEntity<Object> EntidadeNaoEncontradaExpection(
			EntidadeNaoEncontradaExpection ex,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(
			NegocioException ex,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
}

/*
	Anotações
	
	@ControllerAdvice
	essa anotação define que é uma classe do spring
	mas é específica para tratamento de exceções de forma global (para as controllers)
	
	ResponseEntityExceptionHandler
	classe básica para tratamento de exceções
	então estender dela, é para aproveitar os tratamentos que ele já faz
	
	customização (tradução) da mensagem de erro
	fica definido em messages.properties no 'resources'
	
	messageSource
	para usar mensagens customizadas de acordo com o código da excepction
	
	LocaleContextHolder.getLocale()
	recupera o locale (pensando no idioma) de onde está acontecendo a requisição
	
	@ExceptionHandler(NegocioException.class)
	este é o método para tratar qualquer erro relacionado a negocios na aplicação inteira
	
	
*/