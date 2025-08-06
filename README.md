# 🛒 Carrinho Service

Este microserviço controla o **carrinho de compras** do cliente. Ele se comunica com o `loja_service` para buscar os detalhes de produtos e armazena esses dados no banco, simulando o comportamento de um e-commerce real.

---

## 🎯 Responsabilidades

- Adicionar produto ao carrinho consultando o `loja_service`
- Exibir todos os itens adicionados
- Limpar todos os itens do carrinho

---

## 🛠️ Tecnologias e Recursos

- Spring Boot + REST API
- Feign Client (comunicação com `loja_service`)
- Spring Data JPA + MySQL
- Eureka Client para service discovery

---

## 🌐 Integração

- Consome `loja_service` via Feign Client
- Verifica e adiciona produtos no carrinho com base em produtos cadastrados no catálogo

---

## 🚀 Endpoints Principais

- `POST /carrinho/adicionar` → Adiciona produto no carrinho
- `GET /carrinho/mostrarProduto` → Lista os itens do carrinho
- `GET /carrinho/clear` → Limpa o carrinho

---

## 🧠 Aprendizado

- Integração entre microsserviços
- Uso prático de Feign Client para chamadas REST entre serviços
- Persistência de dados com JPA
