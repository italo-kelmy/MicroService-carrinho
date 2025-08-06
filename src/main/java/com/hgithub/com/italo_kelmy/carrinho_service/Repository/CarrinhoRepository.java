package com.hgithub.com.italo_kelmy.carrinho_service.Repository;

import com.hgithub.com.italo_kelmy.carrinho_service.Component.ProdutoDTO;
import com.hgithub.com.italo_kelmy.carrinho_service.Entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {


}
