# Projeto de TCC.
Repositório do Trabalho de Conclusão do Curso (TCC) do curso de Engenharia de Software na UCSAL.

### Tema
>Integração entre LLM e framework agil Scrum no contexto de gestão de projetos ageis.

### Propósito do Sistema Desenvolvido

- Sistema de Agendamento de Eventos
> Uma aplicação web para automatizar o processo de reserva de espaços escolares, substituindo o controle manual em papel.



## Pré-requisitos e Tecnologias Utilizadas
- Java 17;
- PostgreSQL;
- Docker.

## Instalação

### Set-up do Projeto

- Clone este repositório em um diretório conveniente utilizando preferencialmente o Git.

### Set-up do Docker

- Para fazer o set-up do docker contendo o banco de dados, rode o seguinte comando no diretório raíz do projeto:

```bash
docker compose up -d
```

- Verifique se está funcionando com:

```bash
docker compose ps
```

### Configurações das Variáveis de Ambiente.
Para a execução do projeto em um ambiente local, é necessário configurar as variáveis de ambiente, especialmente as que conectam o BackEnd ao Banco de Dados.

Siga as seguintes instruções:

1. No diretório src/main/resources clone o arquivo `application.properties`, com o nome de `application-local.properties`.
2. No novo arquivo de propriedades criado, altere os seguintes campos:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/bancotcc
spring.datasource.username=user
spring.datasource.password=password
```

> Observação: Estes campos refletem as variavéis de ambiente padrão encontradas no `docker-compose.yml`. 

### Inicialização do BackEnd e Execução do projeto

- Para inicializar o BackEnd, execute o script `start-backend.sh` na raiz do diretório.


```bash
./start-backend.sh 
```

## Convenções de branch e PR
> A definir pela equipe.