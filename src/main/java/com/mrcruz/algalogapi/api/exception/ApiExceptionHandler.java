package com.mrcruz.algalogapi.api.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource source;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        StandartError error = new StandartError();

        List<StandartError.Field> fields = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((err)-> {
            String name = ((FieldError) err).getField();
            String message = source.getMessage(err, LocaleContextHolder.getLocale());
            fields.add(new StandartError.Field(name, message));
        });

        error.setStatus(status.value());
        error.setTimestamp(LocalDateTime.now());
        error.setTitle("Um ou mais campos inválidos, preencha corretamente!");
        error.setFields(fields);

        return super.handleExceptionInternal(ex, error, headers, status,request);
    }
}
