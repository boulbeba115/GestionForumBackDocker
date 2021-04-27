package tn.Forum.Main.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class ForumExceptionHundler extends ResponseEntityExceptionHandler{
	@ExceptionHandler
	public final ResponseEntity<Object> handleprojectIfException(ForumException ex, WebRequest request){
		ForumExceptionResponse exceptionResponse = new ForumExceptionResponse((ex.getMessage()));
        return  new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
