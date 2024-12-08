# Cinema Java Project

## Descrição
Este projeto é uma implementação de um sistema de cinema em Java. O projeto está estruturado em várias camadas para garantir a separação de responsabilidades e a fácil manutenção.

## Estrutura do Projeto
O projeto está dividido nas seguintes camadas principais:

### 1. Domain
- Contém todas as regras de negócio do sistema.
- Utiliza o conceito de inversão de dependência para manter o domínio isolado e independente de implementações externas.

### 2. Infra
- Contém as implementações concretas das dependências externas do projeto.
- Inclui o Bcrypt para criptografia de dados e o Hibernate para persistência de dados.

### 3. Application
- Responsável por receber e tratar os dados antes de enviá-los para a camada de domínio.

### 4. Main
- Responsável pela injeção de todas as dependências.
- Gerencia a criação das telas do JavaFX utilizadas no projeto.
- Inicializa o projeto.

## Pré-requisitos
- [Docker e docker compose](https://docs.docker.com/engine/install/)
- Java (Versão 17 ou superior)
- [Maven](https://maven.apache.org/download.cgi)

## Executando o Projeto
Para executar o projeto, siga os passos abaixo:

1. Certifique-se de que o Docker está instalado e em execução na sua máquina.
2. Navegue até o diretório do projeto.
3. Execute o comando:

    ```bash
    docker compose up -d
    ```
    Esse comando irá criar um container do banco de dados postgres

4. Execute o projeto maven:
    ```bash
    mvn clean javafx:run
    ```
    Esse comando irá buildar a aplicação e inicar a tela inicial do projeto

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal.
- **Docker**: Para containerização da aplicação e do banco de dados.
- **PostgreSQL**: Banco de dados relacional utilizado.
- **Bcrypt**: Biblioteca para criptografia de dados.
- **Hibernate**: Framework para persistência de dados.
- **JavaFX**: Biblioteca para construção da interface gráfica.
