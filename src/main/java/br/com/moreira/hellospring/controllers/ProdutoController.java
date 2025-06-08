package br.com.moreira.hellospring.controllers;

import br.com.moreira.hellospring.exceptions.ProdutoDuplicadoException;
import br.com.moreira.hellospring.exceptions.ProdutoNotFountException;
import br.com.moreira.hellospring.models.Produto;
import br.com.moreira.hellospring.services.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProdutoController {
  @Autowired
  private IProdutoService produtoService;

  @PostMapping("/produtos")
  public ResponseEntity<Produto> criarProduto(@RequestBody Produto prod) {
    try {
      Produto produtoCriado = this.produtoService.criarProduto(prod);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(produtoCriado);
    }catch (ProdutoDuplicadoException e) {
      throw e;
    }
  }

  @GetMapping("/produtos/{id}")
  public ResponseEntity<Produto> getProduto(@PathVariable Integer id) {
   try {
     Produto prod = this.produtoService.getProduto(id);
     return ResponseEntity.status(HttpStatus.OK).body(prod);
   } catch (ProdutoNotFountException e) {
     throw e;
   }
  }

  @GetMapping("/produtos")
  public ResponseEntity<ArrayList<Produto>> getProdutos(){
    return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.getProdutos());
  }

  @PatchMapping("/produtos/{id}/desconto/{percentual}")
  public  ResponseEntity<Produto> aplicarDesconto(
      @PathVariable Integer id,
      @PathVariable Double percentual
  ) {
    try {
     Produto prod = this.produtoService.aplicarDesconto(id, percentual);
     return ResponseEntity.status(HttpStatus.OK).body(prod);
    } catch (ProdutoNotFountException e) {
      throw e;
    }
  }
}
