# 🐄 Cowsay Docker API

Uma API REST divertida que combina o clássico utilitário `cowsay` do Unix com Spring Boot, PostgreSQL e Docker. Salve mensagens e receba respostas de uma vaca ASCII falante!

## 📋 Sobre o Projeto

Este projeto é uma aplicação Spring Boot que implementa uma API RESTful para gerenciar mensagens com um toque divertido - as mensagens são exibidas no formato do famoso programa `cowsay`. As mensagens são armazenadas em um banco de dados PostgreSQL e organizadas por remetente.

### ✨ Funcionalidades

- 💾 **Persistência de Mensagens**: Armazene mensagens associadas a um ID de remetente
- 🎲 **Recuperação Aleatória**: Busque uma mensagem aleatória de um remetente específico
- 🐮 **Formatação Cowsay**: Todas as mensagens são retornadas no formato ASCII art da vaca
- 🐳 **Containerização**: Aplicação totalmente dockerizada com Docker Compose
- 🗄️ **Banco de Dados**: Persistência com PostgreSQL

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas típica do Spring Boot:

```
├── Controller (CowsayController) - Endpoints REST
├── Service (CowsayService) - Lógica de negócio e formatação cowsay
├── Repository (MensagemRepository) - Acesso a dados com Spring Data JPA
└── Model (Mensagem) - Entidade JPA
```

## 🛠️ Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.3** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL 16** - Banco de dados relacional
- **Lombok** - Redução de boilerplate
- **Docker & Docker Compose** - Containerização
- **Maven** - Gerenciamento de dependências

## 🚀 Como Executar

### Pré-requisitos

- Docker
- Docker Compose

### Executando a Aplicação

1. Clone o repositório:
```bash
git clone https://github.com/OtavioProcopio/cowsay-docker.git
cd cowsay-docker
```

2. Inicie os containers com Docker Compose:
```bash
docker-compose up --build
```

3. A API estará disponível em `http://localhost:8080`

O Docker Compose irá:
- Construir a imagem da aplicação Spring Boot
- Iniciar um container PostgreSQL
- Configurar a rede entre os containers
- Expor a aplicação na porta 8080

## 📡 Endpoints da API

### Salvar uma Mensagem

**POST** `/cowsay`

```json
{
  "id": "usuario123",
  "msg": "Olá, mundo!"
}
```

**Resposta:**
```
Mensagem Salva!
```

### Buscar Mensagem Aleatória

**GET** `/cowsay/{id}`

Exemplo: `GET http://localhost:8080/cowsay/usuario123`

**Resposta:**
```
 _______
< Olá, mundo! >
 -------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||
```

## 🧪 Exemplos de Uso

### Usando cURL

**Salvar uma mensagem:**
```bash
curl -X POST http://localhost:8080/cowsay \
  -H "Content-Type: application/json" \
  -d '{"id":"john","msg":"Docker é incrível!"}'
```

**Buscar mensagem:**
```bash
curl http://localhost:8080/cowsay/john
```

### Usando httpie

```bash
# Salvar
http POST localhost:8080/cowsay id=john msg="Spring Boot é legal!"

# Buscar
http GET localhost:8080/cowsay/john
```

## 🗃️ Estrutura do Banco de Dados

### Tabela: mensagem

| Coluna | Tipo | Descrição |
|--------|------|-----------|
| id | BIGINT (PK) | ID auto-incrementado |
| remetente_id | VARCHAR | Identificador do remetente |
| conteudo | VARCHAR | Conteúdo da mensagem |

## 🐳 Docker

### Dockerfile

O projeto utiliza multi-stage build para otimizar o tamanho da imagem:

1. **Stage 1 (build)**: Compila a aplicação usando Maven
2. **Stage 2 (runtime)**: Copia apenas o JAR compilado para a imagem final

### Docker Compose

Orquestra dois serviços:
- **cowsay-api**: Aplicação Spring Boot (porta 8080)
- **db**: PostgreSQL 16 (porta 5432)

## ⚙️ Configuração

As configurações da aplicação estão em `src/main/resources/application.properties`:

- URL do banco: `jdbc:postgresql://db:5432/cowsaydb`
- Modo Hibernate: `update` (cria/atualiza tabelas automaticamente)
- SQL logging habilitado para debug

## 🎯 Objetivos do Projeto

Este projeto foi criado para demonstrar:

1. ✅ Desenvolvimento de uma API REST com Spring Boot
2. ✅ Integração com banco de dados PostgreSQL usando JPA
3. ✅ Containerização de aplicações Java com Docker
4. ✅ Uso de Docker Compose para orquestração de múltiplos containers
5. ✅ Multi-stage builds para otimização de imagens Docker
6. ✅ Arquitetura em camadas (Controller, Service, Repository)
7. ✅ Uso de Lombok para código mais limpo

## 🔧 Desenvolvimento

### Executar Localmente (sem Docker)

Se preferir executar sem Docker, certifique-se de ter:
- Java 21 instalado
- PostgreSQL rodando localmente
- Ajustar `application.properties` para apontar para `localhost:5432`

```bash
./mvnw spring-boot:run
```

### Executar Testes

```bash
./mvnw test
```

## 📝 Licença

Este projeto é open source e está disponível para uso educacional.

## 👨‍💻 Autor

**Otávio Procopio**

---

⭐ Se você gostou deste projeto, considere dar uma estrela no repositório!
