package br.com.moreira.hellospring.exceptions;

public class ProdutoNotFountException extends RuntimeException {
  public ProdutoNotFountException(Integer id) {
    super("Não existe um produto com o ID " + id);
  }
}
