package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.exceptions.ErroApi;
import br.com.zup.montivaljunior.desafiocasadocodigo.exceptions.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroApi handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> erros = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());
        return new ErroApi(erros);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroApi handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ErroApi(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroApi handleConstraintViolationException(ConstraintViolationException e) {
        return new ErroApi(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroApi handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErroApi(e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroApi handleIllegalStateException(IllegalStateException e) {
        return new ErroApi(e.getMessage());
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroApi handleRecursoNaoEncontradoException(RecursoNaoEncontradoException e) {
        return new ErroApi(e.getMessage());
    }
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroApi handleRecursoNaoEncontradoException(ResponseStatusException e) {
        return new ErroApi(e.getMessage());
    }

}

