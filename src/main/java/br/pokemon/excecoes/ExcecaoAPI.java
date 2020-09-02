package br.pokemon.excecoes;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

@ControllerAdvice
public class ExcecaoAPI extends ResponseEntityExceptionHandler {

//	@Autowired
//	private MessageSource messageSource;

	@ExceptionHandler(value = ErrosExcecao.class)
	public ResponseEntity<Object> handleConflito(ErrosExcecao ex, WebRequest request) {
		var status = HttpStatus.CONFLICT;
		Excecao excecao = new Excecao();

		excecao.setStatus(status.value());
		excecao.setTitulo(ex.getMessage());
		excecao.setDataHora(OffsetDateTime.now());
		return handleExceptionInternal(ex, excecao, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Excecao excecao = new Excecao();
		List<CamposErros> campos = new ArrayList<>();

		for (ObjectError erros : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) erros).getField();
			//String mensagem = messageSource.getMessage(erros, LocaleContextHolder.getLocale());
			String mensagem = erros.getDefaultMessage();

			campos.add(new CamposErros(nome, mensagem));
		}
		excecao.setStatus(status.value());
		excecao.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		excecao.setDataHora(OffsetDateTime.now());
		excecao.setCampos(campos);
		return super.handleExceptionInternal(ex, excecao, headers, status, request);
	}


}
