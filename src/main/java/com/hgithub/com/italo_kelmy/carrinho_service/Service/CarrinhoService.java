package com.hgithub.com.italo_kelmy.carrinho_service.Service;


import com.hgithub.com.italo_kelmy.carrinho_service.Component.ProdutoDTO;
import com.hgithub.com.italo_kelmy.carrinho_service.Configuration.RabbitMQConfig;
import com.hgithub.com.italo_kelmy.carrinho_service.Entity.Carrinho;
import com.hgithub.com.italo_kelmy.carrinho_service.Feign.ComunicacaoMicroservice;
import com.hgithub.com.italo_kelmy.carrinho_service.Repository.CarrinhoRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ComunicacaoMicroservice microservice;
    private final AmqpTemplate rabbitTemplate;


    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, ComunicacaoMicroservice microservice, AmqpTemplate rabbitTemplate) {
        this.carrinhoRepository = carrinhoRepository;
        this.microservice = microservice;
        this.rabbitTemplate = rabbitTemplate;
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
        rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_COMPRA, produtoId);
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
