package br.com.henrique.emprestimo.exeptions;


import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = { "br.com.henrique.emprestimo.controller" })
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public CustomGlobalExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            body.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleException(final RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.NOT_ACCEPTABLE);
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

}