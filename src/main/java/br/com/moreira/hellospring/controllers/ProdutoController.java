package br.com.moreira.hellospring.controllers;

import br.com.moreira.hellospring.exceptions.ProdutoDuplicadoException;
import br.com.moreira.hellospring.exceptions.ProdutoNotFountException;
import br.com.moreira.hellospring.models.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProdutoController {
  private final ArrayList<Produto> produtos = new ArrayList<>();

  @PostMapping("/produtos")
  public ResponseEntity<Produto> criarProduto(@RequestBody Produto prod) {
    boolean produtoExiste = produtos.stream()
            .anyMatch(p -> p.getId().equals(prod.getId()));
    if(produtoExiste) {
      throw new ProdutoDuplicadoException(prod.getId());
    }

    produtos.add(prod);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(prod);
  }

  @GetMapping("/produtos/{id}")
  public ResponseEntity<Produto> getProduto(@PathVariable Integer id) {
    Produto prod = produtos
        .stream()
        .filter(p -> p.getId().equals(id))
        .findFirst()
        .orElseThrow(() ->  new ProdutoNotFountException(id));

    return ResponseEntity.status(HttpStatus.OK).body(prod);
  }

  @GetMapping("/produtos")
  public ResponseEntity<ArrayList<Produto>> getProdutos(){
    return ResponseEntity.status(HttpStatus.OK).body(produtos);
  }
}
