# 🎬 JavaFlix — API de Cadastro de Streaming

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6CA030?style=for-the-badge&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Lombok](https://img.shields.io/badge/Lombok-red?style=for-the-badge)

Uma API RESTful completa desenvolvida em Java e Spring Boot para o gerenciamento de uma plataforma de streaming. O projeto cobre o cadastro de usuários, autenticação segura com JWT, gerenciamento de filmes, categorias e planos de streaming — tudo com arquitetura limpa e boas práticas de mercado.

---

## 🎯 Sobre o Projeto

O JavaFlix é um back-end de uma plataforma de streaming que permite:

- **Autenticação e autorização** de usuários via tokens JWT
- **Gestão completa de filmes** (cadastro, busca, atualização e remoção)
- **Organização por categorias** para facilitar a navegação no catálogo
- **Controle de planos de streaming** com diferentes níveis de acesso
- **Cadastro e gerenciamento de usuários** com segurança integrada

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| **Java 17** | Linguagem principal |
| **Spring Boot** | Framework base da aplicação |
| **Spring Web MVC** | Criação dos endpoints REST |
| **Spring Data JPA / Hibernate** | Mapeamento objeto-relacional e acesso ao banco |
| **Spring Security** | Autenticação, autorização e proteção das rotas |
| **Auth0 Java JWT 4.5.1** | Geração e validação de tokens JWT |
| **Flyway** | Controle de versão e migrações do banco de dados |
| **PostgreSQL** | Banco de dados relacional |
| **SpringDoc OpenAPI (Swagger) 2.8.17** | Documentação interativa da API |
| **Jakarta Bean Validation** | Validação de dados de entrada |
| **Lombok** | Redução de código boilerplate |

---

## 🧠 Arquitetura e Padrões Aplicados

O projeto foi estruturado seguindo padrões consolidados de mercado para APIs profissionais:

- **Arquitetura em Camadas:** Divisão clara entre `Controller`, `Service`, `Repository`, `Mapper` e `Entity`, garantindo separação de responsabilidades.
- **Padrão DTO (Request/Response):** Uso de classes dedicadas para entrada (`*Request`) e saída (`*Response`) de dados, isolando as entidades do banco do contrato da API.
- **Segurança Stateless com JWT:** Implementação de um filtro customizado (`SecurityFilter`) que intercepta cada requisição, valida o token JWT via `TokenService` e injeta o usuário autenticado no contexto do Spring Security (`JWTUserData`).
- **Controle de Acesso por Rotas:** Configuração centralizada em `SecurityConfig` para definir quais endpoints são públicos (login, cadastro) e quais exigem autenticação.
- **Migrações Gerenciadas com Flyway:** Todo o schema do banco de dados é versionado e aplicado automaticamente via scripts SQL na inicialização da aplicação.
- **Tratamento Global de Exceções:** `ApplicationControllerAdvice` com `@RestControllerAdvice` centraliza o tratamento de erros, retornando respostas padronizadas (ex.: `UsernameOrPasswordInvalidException`).
- **Documentação Automática com Swagger:** Todos os endpoints são documentados e testáveis via interface gráfica gerada pelo SpringDoc OpenAPI.

---

## 📁 Estrutura do Projeto

```
javaflix/
├── config/
│   ├── ApplicationControllerAdvice.java  # Tratamento global de exceções
│   ├── JWTUserData.java                  # Dados do usuário autenticado
│   ├── SecurityConfig.java               # Configuração do Spring Security
│   ├── SecurityFilter.java               # Filtro de autenticação JWT
│   └── TokenService.java                 # Geração e validação de tokens JWT
├── controller/
│   ├── request/                          # DTOs de entrada
│   │   ├── CategoriaRequest.java
│   │   ├── FilmeRequest.java
│   │   ├── LoginRequest.java
│   │   ├── StreamingRequest.java
│   │   └── UsuarioRequest.java
│   ├── response/                         # DTOs de saída
│   │   ├── CategoriaResponse.java
│   │   ├── FilmeResponse.java
│   │   ├── LoginResponse.java
│   │   ├── StreamingResponse.java
│   │   └── UsuarioResponse.java
│   ├── AutenticacaoController.java
│   ├── CategoriaController.java
│   ├── FilmeController.java
│   └── StreamingController.java
├── entity/
│   ├── Categoria.java
│   ├── Filme.java
│   ├── Streaming.java
│   └── Usuario.java
├── exception/
│   └── UsernameOrPasswordInvalidException.java
├── mapper/                               # Conversão Entity <-> DTO
├── repository/
│   ├── CategoriaRepository.java
│   ├── FilmeRepository.java
│   ├── StreamingRepository.java
│   └── UsuarioRepository.java
└── service/
    ├── AutenticacaoService.java
    ├── CategoriaService.java
    ├── FilmeService.java
    ├── StreamingService.java
    └── UsuarioService.java
```

---

## 🛠️ Como Executar o Projeto Localmente

**Pré-requisitos:** Java 17 (ou superior), Maven e uma instância do PostgreSQL rodando localmente.

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/javaflix.git
   ```

2. Crie um banco de dados no PostgreSQL:
   ```sql
   CREATE DATABASE javaflix;
   ```

3. Ajuste as credenciais no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/javaflix
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha

   api.security.token.secret=sua_chave_secreta_jwt
   ```

4. Execute a aplicação via Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

> O Flyway aplicará automaticamente todas as migrações de banco de dados na primeira execução.

---

## 📖 Documentação dos Endpoints

A URL base da aplicação rodando localmente é `http://localhost:8080`.

A documentação interativa completa (Swagger UI) está disponível em:
`http://localhost:8080/swagger-ui/index.html`

### Autenticação

| Método | Rota | Autenticação | Descrição |
|---|---|---|---|
| `POST` | `/auth/login` | ❌ Pública | Autentica um usuário e retorna o token JWT. |
| `POST` | `/auth/register` | ❌ Pública | Cadastra um novo usuário na plataforma. |

### Filmes

| Método | Rota | Autenticação | Descrição |
|---|---|---|---|
| `GET` | `/filmes` | ✅ Requerida | Lista todos os filmes do catálogo. |
| `GET` | `/filmes/{id}` | ✅ Requerida | Busca um filme específico pelo ID. |
| `POST` | `/filmes` | ✅ Requerida | Cadastra um novo filme. |
| `PUT` | `/filmes/{id}` | ✅ Requerida | Atualiza os dados de um filme existente. |
| `DELETE` | `/filmes/{id}` | ✅ Requerida | Remove um filme do catálogo. |

### Categorias

| Método | Rota | Autenticação | Descrição |
|---|---|---|---|
| `GET` | `/categorias` | ✅ Requerida | Lista todas as categorias. |
| `POST` | `/categorias` | ✅ Requerida | Cadastra uma nova categoria. |
| `PUT` | `/categorias/{id}` | ✅ Requerida | Atualiza uma categoria existente. |
| `DELETE` | `/categorias/{id}` | ✅ Requerida | Remove uma categoria. |

### Planos de Streaming

| Método | Rota | Autenticação | Descrição |
|---|---|---|---|
| `GET` | `/streaming` | ✅ Requerida | Lista todos os planos de streaming. |
| `POST` | `/streaming` | ✅ Requerida | Cadastra um novo plano. |
| `PUT` | `/streaming/{id}` | ✅ Requerida | Atualiza um plano existente. |
| `DELETE` | `/streaming/{id}` | ✅ Requerida | Remove um plano. |

---

## 🔐 Fluxo de Autenticação

1. O cliente envia `POST /auth/login` com as credenciais (usuário e senha).
2. A API valida as credenciais e retorna um **token JWT**.
3. Para acessar rotas protegidas, o cliente inclui o token no header:
   ```
   Authorization: Bearer <token>
   ```
4. O `SecurityFilter` intercepta a requisição, valida o token via `TokenService` e libera o acesso.

### Exemplo: Login (POST /auth/login)

```json
{
  "login": "usuario@email.com",
  "senha": "senha123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Exemplo: Cadastrar Filme (POST /filmes)

```json
{
  "titulo": "Interestelar",
  "descricao": "Uma equipe de astronautas viaja por um buraco de minhoca em busca de um novo lar para a humanidade.",
  "anoLancamento": 2014,
  "categoriaId": 1
}
```
