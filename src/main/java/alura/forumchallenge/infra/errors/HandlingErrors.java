package alura.forumchallenge.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlingErrors {

    private record ValidatingErrorData(String field, String error){
        public ValidatingErrorData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity handlingError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity handlingError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ValidatingErrorData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    //

}
