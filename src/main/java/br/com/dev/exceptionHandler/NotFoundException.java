package br.com.dev.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Require's not found")
public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
