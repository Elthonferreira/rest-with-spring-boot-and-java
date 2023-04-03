package br.com.elthon.exceptions.handler;

import java.util.Date;

import br.com.elthon.exceptions.RequiredObjectIsNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.elthon.exceptions.ExceptionResponse;
import br.com.elthon.exceptions.ResourceNotFoundException;
import br.com.elthon.exceptions.UnsupportedMathOperationException;

@ControllerAdvice
@RestController
public class CustomizedReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		
		//COMENTEI O CÓDIGO DO PROFESSOR, PORQUE O CÓDIGO ABAIXO É MAIS INTUITIVO PARA MIM
		/* 
		  ExceptionResponse exceptionResponse = new ExceptionResponse( new Date(),
		  ex.getMessage(), request.getDescription(false));
		*/
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		
		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setDetails(request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnsupportedMathOperationException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestMathExceptions(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setDetails(request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setDetails(request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setDetails(request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
