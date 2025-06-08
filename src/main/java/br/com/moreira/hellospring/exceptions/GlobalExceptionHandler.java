package br.com.moreira.hellospring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ProdutoDuplicadoException.class)
  public ResponseEntity<ErrorResponseDTO> handleProdutoDuplicadoException(ProdutoDuplicadoException e) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponseDTO(e.getMessage()));
  }

  @ExceptionHandler(ProdutoNotFountException.class)
  public ResponseEntity<ErrorResponseDTO> handleProdutoNotFoundException(ProdutoNotFountException e) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponseDTO(e.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGenericErrors(Exception e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponseDTO(e.getMessage()));
  }
}
