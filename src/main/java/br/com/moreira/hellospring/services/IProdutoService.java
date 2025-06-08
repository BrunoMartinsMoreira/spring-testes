package br.com.moreira.hellospring.services;

import br.com.moreira.hellospring.exceptions.ProdutoDuplicadoException;
import br.com.moreira.hellospring.exceptions.ProdutoNotFountException;
import br.com.moreira.hellospring.models.Produto;

import java.util.ArrayList;

public interface IProdutoService {
  public Produto criarProduto(Produto prod) throws ProdutoDuplicadoException;
  public Produto getProduto(Integer id) throws ProdutoNotFountException;
  public ArrayList<Produto> getProdutos();
  public Produto aplicarDesconto(Integer id, double percentual) throws ProdutoNotFountException;
}
