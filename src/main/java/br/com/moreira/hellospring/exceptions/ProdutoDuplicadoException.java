package br.com.moreira.hellospring.exceptions;

public class ProdutoDuplicadoException extends RuntimeException {
  public ProdutoDuplicadoException(Integer id) {
    super("Já existe um produto com o ID " + id);
  }
}
