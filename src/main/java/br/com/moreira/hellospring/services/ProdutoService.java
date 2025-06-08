package br.com.moreira.hellospring.services;

import br.com.moreira.hellospring.exceptions.ProdutoDuplicadoException;
import br.com.moreira.hellospring.exceptions.ProdutoNotFountException;
import br.com.moreira.hellospring.models.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProdutoService implements IProdutoService {
  private final ArrayList<Produto> produtos = new ArrayList<>();

  @Override
  public Produto criarProduto(Produto prod) throws ProdutoDuplicadoException {
    boolean produtoExiste = this.produtos.stream()
        .anyMatch(p -> p.getId().equals(prod.getId()));

    if(produtoExiste) {
      throw new ProdutoDuplicadoException(prod.getId());
    }

    this.produtos.add(prod);
    return prod;
  }

  @Override
  public Produto getProduto(Integer id) throws ProdutoNotFountException {

    return produtos
        .stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .orElseThrow(() ->  new ProdutoNotFountException(id));
  }

  @Override
  public ArrayList<Produto> getProdutos() {
    return this.produtos;
  }

  @Override
  public Produto aplicarDesconto(Integer id, double percentual) throws ProdutoNotFountException {
    Produto prod = this.getProduto(id);

    double precoComDesconto = prod.getPreco() - prod.getPreco() * (percentual / 100);
    prod.setPreco(precoComDesconto);
    return prod;
  }
}
