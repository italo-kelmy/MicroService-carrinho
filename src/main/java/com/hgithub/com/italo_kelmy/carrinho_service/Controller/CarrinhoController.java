package com.hgithub.com.italo_kelmy.carrinho_service.Controller;

import com.hgithub.com.italo_kelmy.carrinho_service.Component.ProdutoDTO;
import com.hgithub.com.italo_kelmy.carrinho_service.Entity.Carrinho;

import com.hgithub.com.italo_kelmy.carrinho_service.Service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService service;

    @Autowired
    public CarrinhoController(CarrinhoService service) {
        this.service = service;
    }


    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarProduto(@RequestBody ProdutoDTO produtoId) {

        int quantidade = produtoId.getQuantidade();

        return service.adicionarNoCarrinho(produtoId, quantidade);
    }

    @GetMapping("/mostrarProduto")
    public ResponseEntity<?> produto() {
        return service.mostrarCarrinho();
    }

    @GetMapping("/clear")
    public ResponseEntity<?> limparProdutosDoCarrinho() {
        return service.limparCarrinho();
    }
}
