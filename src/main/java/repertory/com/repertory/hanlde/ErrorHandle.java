package repertory.com.repertory.hanlde;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import repertory.com.repertory.exeption.ResourceNotFoundException;

@ControllerAdvice
public class ErrorHandle extends ResponseEntityExceptionHandler  {

	
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<ErrorResponseDto> handleConflict(ResourceNotFoundException ex) {
		return new ResponseEntity<>(new ErrorResponseDto("Resource not found"), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { Exception.class})
	protected ResponseEntity<ErrorResponseDto> handleConflict(Exception ex) {
		return new ResponseEntity<>(new ErrorResponseDto("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
