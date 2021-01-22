package platform.controller.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import platform.exception.CodeNotFoundException;
import platform.exception.ValidationException;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(CodeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Code not found")
    public void onCodeNotFoundException() {
        // No operations.
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> onValidationException(ValidationException e) {
        String errorMessage = getErrorMessage(e.getBindingResult());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private static String getErrorMessage(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                return fieldError.getField() + " " + fieldError.getDefaultMessage();
            }
            return error.getDefaultMessage();
        }
        return null;
    }
}
