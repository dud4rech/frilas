# Frilas.com 💻🤝💸

Este trabalho tem como objetivo o desenvolvimento de um banco de dados para a Frilas.com, uma plataforma que conecta freelancers da área de Tecnologia da Informação (TI) a contratantes em busca de serviços. A aplicação permite que contratantes publiquem projetos com descrições e orçamentos, recebam propostas de profissionais interessados e escolham aquele que melhor atende às suas necessidades. Após a execução do projeto, é possível avaliar o freelancer.

O banco de dados foi estruturado para armazenar informações sobre freelancers, contratantes, projetos, propostas, pagamentos e avaliações. Ainda, permite a geração de três relatórios: propostas enviadas para um projeto, histórico de transações financeiras por contratante e avaliações recebidas por freelancer.

## Pré-requisitos

1. PostgreSql e um administrador de banco como o PgAdmin; 
2. Java (21);
3. Maven

## Instalação

Clonar o repositório
```
git clone https://github.com/dud4rech/frilas
cd frilas
```
Instalar as dependências
```
mvn clean install
```

## Configuração do banco

1. Criar um novo database utilizando o administrador de banco;
2. Rodar o dump do banco;
2. Acessar src/main/java/org/project/connection/DBConnection.java;
2. Alterar os dados de configuração de acordo com o seu ambiente:
```
   jdbc.url=jdbc:postgresql://localhost:5432/{nome do database}
   jdbc.username={seu username}
   jdbc.password={sua senha}
```

## Compilar e executar a aplicação
```
mvn clean compile
```

```
mvn exec:java -Dexec.mainClass="org.project.Main"
```