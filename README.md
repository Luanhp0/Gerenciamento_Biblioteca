# Gerenciamento de Biblioteca

Sistema web de gerenciamento de biblioteca desenvolvido em Java com Jakarta EE, utilizando o padrão Command Factory e arquitetura MVC. Permite o controle de livros físicos e digitais, empréstimos, usuários e notificações automáticas de devolução.


## tecnologias utilizadas

- Java (JDK 24)
- Apache Tomcat 9
- MySQL
- JSP e Servlets
- JDBC
- Quartz Scheduler (notificações automáticas)
- Padrão de projeto: Command Factory, Builder, Decorator


## funcionalidades

**livros**
- cadastro de livros físicos (com tipo de capa e disponibilidade) e digitais (com formato e tamanho)
- consulta, edição e exclusão de livros
- ficha técnica com páginas, editora, idioma e ano de publicação

**usuários**
- cadastro com roles: admin, bibliotecário e leitor
- tipos: aluno, professor e pesquisador
- login e logout com controle de sessão
- notificações por SMS, e-mail e WhatsApp (via Decorator)

**empréstimos**
- cadastro de empréstimo vinculando livro e usuário
- prazo calculado automaticamente conforme tipo do usuário (15 ou 30 dias)
- registro de devolução
- consulta de empréstimos por usuário (painel do leitor)
- validação para evitar empréstimo duplicado

**notificações automáticas**
- job agendado com Quartz Scheduler usando SimpleTrigger
- alerta quando faltam 3 dias ou menos para devolução
- alerta quando o prazo está vencido



## como rodar o projeto

1. clone o repositório
2. importe no NetBeans como projeto existente
3. configure o banco de dados MySQL com o script `banco_aulapp.sql`
4. adicione os JARs ao projeto: `quartz-2.4.1.jar`, `slf4j-api-1.7.36.jar`, `slf4j-simple-1.7.36.jar` e o conector MySQL
5. configure a conexão em `src/java/util/Conexao.java` com seu usuário e senha do MySQL
6. suba o Tomcat e acesse `http://localhost:8080/Gerenciamento_Biblioteca`


## banco de dados

tabelas principais: `livros`, `fichatecnica`, `emprestimo`, `usuarios`, `role`, `notificacao`

login padrão de admin:
- email: `admin@biblioteca.com`
- senha: `admin123`


## padrões de projeto utilizados

- **command factory** → cada ação do sistema é uma classe separada, facilitando manutenção
- **builder** → construção de objetos complexos como Livro, Emprestimo e Usuario
- **decorator** → sistema de notificações com SMS, e-mail e WhatsApp empilháveis
- **dao** → separação total entre regra de negócio e acesso a dados
