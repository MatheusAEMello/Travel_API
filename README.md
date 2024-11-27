
# Travel API

Travel API é uma aplicação RESTful desenvolvida em Java utilizando o framework Spring Boot. O objetivo da aplicação é gerenciar destinos turísticos, permitindo operações como criação, atualização, busca, reserva e avaliação de destinos.

## Endpoints

### 1. Criar Destino
- **URL**: `/api/destinations`
- **Método HTTP**: `POST`
- **Descrição**: Cria um novo destino com base nos dados fornecidos.
- **Exemplo de Requisição**:
  ```json
  {
    "name": "Praia do Rosa",
    "location": "Santa Catarina, Brasil",
    "description": "Uma das praias mais bonitas do Brasil."
  }
  ```

### 2. Criar Vários Destinos (Bulk)
- **URL**: `/api/destinations/bulk`
- **Método HTTP**: `POST`
- **Descrição**: Cria vários destinos de uma só vez.
- **Exemplo de Requisição**:
  ```json
  [
    {
      "name": "Praia do Forte",
      "location": "Bahia, Brasil",
      "description": "Um destino encantador no litoral baiano."
    },
    {
      "name": "Lençóis Maranhenses",
      "location": "Maranhão, Brasil",
      "description": "Um dos lugares mais impressionantes do Brasil."
    }
  ]
  ```

### 3. Buscar Todos os Destinos
- **URL**: `/api/destinations`
- **Método HTTP**: `GET`
- **Descrição**: Retorna uma lista de todos os destinos cadastrados.

### 4. Buscar Destino por ID
- **URL**: `/api/destinations/{id}`
- **Método HTTP**: `GET`
- **Descrição**: Retorna as informações de um destino específico.
- **Exemplo de Resposta**:
  ```json
  {
    "id": 1,
    "name": "Praia do Rosa",
    "location": "Santa Catarina, Brasil",
    "description": "Uma das praias mais bonitas do Brasil.",
    "averageRating": 0.0,
    "totalRatings": 0,
    "totalReservations": 0
  }
  ```

### 5. Reservar Destino
- **URL**: `/api/destinations/{id}/reserve`
- **Método HTTP**: `POST`
- **Descrição**: Incrementa o número de reservas de um destino específico.

### 6. Adicionar Avaliação
- **URL**: `/api/destinations/{id}/rating`
- **Método HTTP**: `PATCH`
- **Descrição**: Adiciona uma avaliação ao destino.
- **Parâmetro**: `rating` (double)
- **Exemplo de Requisição**:
  ```
  /api/destinations/1/rating?rating=9.5
  ```

### 7. Atualizar Destino
- **URL**: `/api/destinations/{id}`
- **Método HTTP**: `PUT`
- **Descrição**: Atualiza as informações de um destino.
- **Exemplo de Requisição**:
  ```json
  {
    "name": "Praia do Rosa Atualizada",
    "location": "Santa Catarina, Brasil",
    "description": "Uma praia ainda mais bonita."
  }
  ```

### 8. Deletar Destino
- **URL**: `/api/destinations/{id}`
- **Método HTTP**: `DELETE`
- **Descrição**: Remove um destino do sistema.

## Exemplos de Testes e Relações

### Fluxo Completo

1. **Criar um Destino**:
   ```json
   {
     "name": "Fernando de Noronha",
     "location": "Pernambuco, Brasil",
     "description": "Um arquipélago paradisíaco."
   }
   ```

2. **Reservar o Destino Criado**:
   ```
   POST /api/destinations/1/reserve
   ```

3. **Adicionar uma Avaliação**:
   ```
   PATCH /api/destinations/1/rating?rating=10
   ```

4. **Atualizar o Destino**:
   ```json
   {
     "name": "Fernando de Noronha - Atualizado",
     "location": "Pernambuco, Brasil",
     "description": "Ainda mais encantador."
   }
   ```

5. **Buscar o Destino Atualizado**:
   ```
   GET /api/destinations/1
   ```

6. **Deletar o Destino**:
   ```
   DELETE /api/destinations/1
   ```

## Configurações

### Banco de Dados
O projeto utiliza o banco de dados em memória H2. As configurações estão no arquivo `application.properties`.

### Dependências
As principais dependências incluem:
- Spring Boot Web
- Spring Boot Validation
- H2 Database
- Lombok

## Como Executar

1. Clone o repositório.
2. Certifique-se de que o Maven esteja instalado.
3. Execute o comando:
   ```
   mvn spring-boot:run
   ```
4. Acesse a aplicação no endereço: `http://localhost:8080/api/destinations`.

