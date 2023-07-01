package br.com.springwithobservability.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericError extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserError.class)
	public ResponseEntity<Object> ErrorUser(UserError error, WebRequest request){
		
		return super.handleExceptionInternal(error, error.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

}
