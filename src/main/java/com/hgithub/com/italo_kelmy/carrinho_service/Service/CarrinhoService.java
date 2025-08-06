package com.hgithub.com.italo_kelmy.carrinho_service.Service;


import com.hgithub.com.italo_kelmy.carrinho_service.Component.ProdutoDTO;
import com.hgithub.com.italo_kelmy.carrinho_service.Entity.Carrinho;
import com.hgithub.com.italo_kelmy.carrinho_service.Feign.ComunicacaoMicroservice;
import com.hgithub.com.italo_kelmy.carrinho_service.Repository.CarrinhoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ComunicacaoMicroservice microservice;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, ComunicacaoMicroservice microservice) {
        this.carrinhoRepository = carrinhoRepository;
        this.microservice = microservice;
    }

    @Transactional
    public ResponseEntity<?> adicionarNoCarrinho(ProdutoDTO produtoId, int quantidade) {
        ProdutoDTO response = microservice.buscarProdutoPorId(produtoId);


        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        Carrinho carrinho = new Carrinho(
                response.getId(),
                response.getNome(),
                response.getCategoria(),
                response.getDescricao(),
                quantidade,
                response.getValor()
        );

        carrinhoRepository.save(carrinho);

        return ResponseEntity.ok("Produto adicionado no carrinho com sucesso");
    }


    public ResponseEntity<?> mostrarCarrinho() {
        List<Carrinho> carrinhoList = carrinhoRepository.findAll();

        if (carrinhoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho está vazio");
        }

        return ResponseEntity.ok(carrinhoList);
    }

    public ResponseEntity<?> limparCarrinho() {
        carrinhoRepository.deleteAll();
        return ResponseEntity.ok("Carrinho limpado com sucesso");
    }


}
