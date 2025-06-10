# CRUD Manager - Loja de Celulares

Este é um projeto web desenvolvido em Java, utilizando Servlets e JSP, com foco em operações CRUD (Create, Read, Update, Delete) para gerenciar dados de uma loja de celulares. Além disso, inclui um mecanismo de autenticação (Login) para controlar o acesso.

## Tecnologias Utilizadas

* **Linguagem:** Java
* **Servidor Web:** Apache Tomcat (v9.x ou superior)
* **Banco de Dados:** MySQL
* **Web Frameworks/APIs:**
    * Jakarta EE (Servlets, JSP)
    * JSP Standard Tag Library (JSTL)
* **Gerenciador de Dependências:** Apache Maven
* **Estilização:** Bootstrap 4

## Funcionalidades

* **CRUD de Celulares:**
    * Listagem de celulares.
    * Cadastro de novos celulares (com campos como Modelo, Cor, Armazenamento, Preço, Data de Lançamento e uma **Marca** relacionada).
    * Edição de celulares existentes.
    * Exclusão de celulares.
* **CRUD de Usuários:**
    * Listagem, cadastro, edição e exclusão de usuários.
* **CRUD de Empresas:**
    * Listagem, cadastro, edição e exclusão de empresas.
* **Autenticação de Usuários:**
    * Página de Login para acesso restrito.
    * Página de Logout.
    * Filtro de segurança para proteger rotas da aplicação.
