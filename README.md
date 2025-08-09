# 🛒 Carrinho Service

Este microserviço gerencia o **carrinho de compras** do cliente.  
Agora, com **integração RabbitMQ**, ele envia automaticamente uma mensagem para a fila assim que um produto é adicionado, permitindo que o `compra_service` processe a compra de forma **assíncrona**.

---

## 🎯 Responsabilidades

- Adicionar produto ao carrinho consultando o `loja_service` via Feign
- Listar todos os itens do carrinho
- Limpar o carrinho
- **Enviar mensagens para a fila do RabbitMQ** para iniciar o processo de compra

---

## 🛠️ Tecnologias e Recursos

- Spring Boot + REST API
- Spring Data JPA + MySQL
- Spring Cloud OpenFeign
- Eureka Client
- **Spring AMQP (RabbitMQ)** com `Jackson2JsonMessageConverter` para serialização automática
- Fila configurada: `compra.fila`

---

## 🌐 Integração

- **Sincronamente**: consulta o `loja_service` para buscar informações do produto
- **Assincronamente**: envia os dados do produto para o `compra_service` via RabbitMQ

---

## 🚀 Endpoints Principais

- `POST /carrinho/adicionar` → Adiciona produto e envia mensagem para RabbitMQ
- `GET /carrinho/mostrarProduto` → Lista os itens do carrinho
- `GET /carrinho/clear` → Limpa o carrinho

---

## 🔄 Fluxo com RabbitMQ

1. Cliente adiciona um produto no carrinho (`POST /carrinho/adicionar`)
2. O serviço salva o item no banco
3. Os dados do produto são enviados para a fila `compra.fila`
4. O `compra_service` consome essa mensagem e processa a compra

---

## 🧠 Aprendizado

- Implementação de comunicação assíncrona entre microserviços
- Uso de filas para desacoplar o fluxo de compra
- Serialização automática de objetos para JSON no RabbitMQ
