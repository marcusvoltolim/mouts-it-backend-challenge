# Mouts IT - Backend Challenge

Este repositório contém a solução para o desafio de backend proposto pela Mouts IT, desenvolvido com JAVA e Spring Boot.

## 📋 Sobre o Desafio

O desafio consiste em desenvolver uma API RESTFULL para gerenciamento de pedidos, utilizando boas práticas de desenvolvimento e arquitetura de software.
Também implementado integração via mensageria usando SQS.


## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 23
- **Framework:** Spring Boot 3.4.3
- **Gerenciador de Dependências:** Maven
- **Banco de Dados:** MongoDB
- **Containerização:** Docker e Docker Compose

## 🚀 Como Executar o Projeto

### 🔧 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:
- [Java 23](https://www.oracle.com/br/java/technologies/downloads/#java23)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)

### 📦 Instalação e Execução

1. Clone este repositório:
   ```sh
   git clone https://github.com/marcusvoltolim/mouts-it-backend-challenge.git
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd mouts-it-backend-challenge
   ```
3. Configure as variáveis de ambiente:
    - Modifique o arquivo `docker-compose.yaml` ou defina um arquivo `.env` com as configurações necessárias.

4. Execute o projeto com Docker Compose:
   ```sh
   docker-compose up --build
   ```
   Ou execute manualmente com Maven:
   ```sh
   mvn spring-boot:run
   ```

## 🧪 Testes

Para rodar os testes automatizados, utilize:
```sh
mvn test
```

## 📌 Endpoints Principais

- `GET /order` - Lista todos os pedidos
- `GET /order/{id}` - Obtém um pedido especifico
- `POST /order` - Cria um novo pedido
   ```json
   {
       "id": "e6241905-d54d-41d3-8026-d126f3172055",
       "products": [
           {
               "name": "Book 1",
               "price": 55.00
           },
            {
               "name": "Book 2",
               "price": 50.00
           }
       ]
   }
   ```

## 👥 Autor

Criado por [Marcus Voltolim](https://github.com/marcusvoltolim).
