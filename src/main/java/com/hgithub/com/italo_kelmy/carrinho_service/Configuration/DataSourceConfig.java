package com.hgithub.com.italo_kelmy.carrinho_service.Configuration;
import org.hibernate.annotations.FilterJoinTable;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DataSourceConfig {

    // Configuração do banco de dados Carrinho
    @Primary
    @Bean(name = "dataSourceCarrinho")
    public DataSource dataSourceCarrinho() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/carrinho?useSSL=false&serverTimezone=UTC")
                .username("italo")
                .password("2610!!")
                .build();
    }

    // Configuração do banco de dados Produtos Eletrônicos
    @Bean(name = "dataSourceProdutos")
    public DataSource dataSourceProdutos() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/produtoseletronicos?useSSL=false&serverTimezone=UTC")
                .username("italo")
                .password("2610!!")
                .build();
    }


}
