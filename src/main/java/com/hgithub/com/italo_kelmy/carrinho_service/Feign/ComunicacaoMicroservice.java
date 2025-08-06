package com.hgithub.com.italo_kelmy.carrinho_service.Feign;


import com.hgithub.com.italo_kelmy.carrinho_service.Component.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@FeignClient(name = "loja-service", url = "https://loja-service:8443", path = "produtos")
public interface ComunicacaoMicroservice {
    @PostMapping("/buscarPeloId")
    ProdutoDTO buscarProdutoPorId(@RequestBody ProdutoDTO id);

}