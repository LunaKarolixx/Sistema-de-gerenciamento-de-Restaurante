# Sistema de Gerenciamento de Restaurante - Comande Aqui

## Visão Geral

Este projeto foi desenvolvido para a disciplina de **Linguagem de Programação II**. Trata-se de uma aplicação de console em **Java** que simula um sistema de gerenciamento para um restaurante.

Na sua essência, o projeto é um sistema **CRUD (Create, Read, Update, Delete)** completo, aplicado ao domínio de um restaurante. Ele permite a manipulação de dados de entidades como produtos, clientes, garçons e pedidos, utilizando os conceitos fundamentais da Programação Orientada a Objetos (POO).

## Tecnologias Utilizadas

* **Java**: Linguagem principal do projeto.
* **Programação Orientada a Objetos (POO)**: Paradigma central utilizado para a modelagem do sistema.

## Funcionalidades Principais

* **Cadastro de Entidades**: Permite registrar novos produtos no cardápio, clientes e garçons.
* **Criação e Gestão de Pedidos**: O usuário pode criar novos pedidos, associá-los a uma mesa e adicionar múltiplos itens, com controle de quantidade.
* **Gerenciamento de Mesas**: O sistema monitora o status de cada mesa (livre ou ocupada), exibindo qual cliente e garçom estão associados a ela.
* **Pagamento de Contas**: Calcula o valor total da conta de uma mesa (considerando todos os pedidos em aberto e uma taxa de serviço) e processa o pagamento, liberando a mesa em seguida.
* **Interface de Terminal**: Todas as interações são feitas através de um menu de texto no console, com tratamento de erros para entradas inválidas.

## Requisitos Mínimos Atendidos

O projeto foi desenvolvido para cumprir uma lista de requisitos obrigatórios da disciplina, detalhados abaixo:

#### 1. Mínimo 8 Classes
O sistema foi modelado com as seguintes 8 classes:
- `Pessoa` (abstrata)
- `Cliente`
- `Garcom`
- `Produto`
- `Mesa`
- `Pedido`
- `Pagamento`
- `Main`

#### 2. CRUD (Create, Read, Update, Delete) e Tratamento de Exceções
O foco central do projeto é a implementação de um sistema CRUD completo. As operações de manipulação de dados para 5 entidades principais foram implementadas da seguinte forma:

* **Produto:**
    * **Create:** Cadastro de novos itens no cardápio (Menu: 1).
    * **Read:** Listagem de todos os produtos disponíveis (Menu: 7).

* **Cliente:**
    * **Create:** Cadastro de novos clientes (Menu: 2).
    * **Read:** Seleção de clientes durante a criação de um pedido.

* **Garçom:**
    * **Create:** Cadastro de novos garçons (Menu: 3).
    * **Read:** Seleção de garçons durante a criação de um pedido.

* **Mesa:**
    * **Read:** Visualização do status de todas as mesas (livre/ocupada, cliente, garçom) (Menu: 5).
    * **Update:** O estado da mesa (`ocupada`, `clienteOcupante`) é atualizado indiretamente quando um pedido é criado ou um pagamento é efetuado.

* **Pedido:**
    * **Create:** Criação de um novo pedido para uma mesa (Menu: 4).
    * **Update:** Adição de novos produtos a um pedido existente (também no Menu 4) e remoção de produtos (Menu: 8).
    * **Delete:** A remoção de itens de um pedido (Menu: 8) funciona como uma operação de "delete" parcial.

A robustez da aplicação é garantida pelo **tratamento de exceções** com blocos `try-catch`, que previnem que o programa encerre inesperadamente devido a entradas inválidas do usuário (ex: `InputMismatchException`, `IndexOutOfBoundsException`).

#### 3. Relacionamentos
Foram implementados os três tipos de relacionamentos solicitados:
* **1:1 (Um para Um)**: Entre `Pedido` e `Pagamento`. Cada pedido pode ter apenas um registro de pagamento associado.
* **1:N (Um para N)**: Entre `Mesa` e `Pedido`. Uma mesa pode conter múltiplos pedidos durante o período em que está ocupada.
* **N:M (N para M)**: Entre `Pedido` e `Produto`. Um pedido pode ter vários produtos, e um produto pode estar em vários pedidos. Esta relação foi implementada através de um `Map<Produto, Integer>` na classe `Pedido`.

#### 4. Encapsulamento
Todos os atributos das classes de modelo foram declarados com o modificador de acesso `private`. O acesso e a modificação desses atributos são controlados por meio de métodos públicos (`getters`), garantindo a segurança e a integridade dos dados dos objetos.

#### 5. Herança
A herança foi aplicada na criação da superclasse abstrata `Pessoa`, que define características comuns. As classes `Cliente` e `Garcom` herdam de `Pessoa`, especializando-a com seus próprios atributos e comportamentos.

#### 6. Polimorfismo
O polimorfismo foi demonstrado através da sobrescrita (`@Override`) do método `exibirDados()`. Este método, declarado como abstrato em `Pessoa`, é implementado de formas diferentes por `Cliente` e `Garcom`.

#### 7. Classes Abstratas
A classe `Pessoa` foi definida como `abstract`, servindo como um modelo que não pode ser instanciado, forçando a especialização através de suas subclasses.

## Como Executar o Projeto

1.  Clone o repositório para a sua máquina local.
2.  Navegue até o diretório `src` pelo terminal.
3.  Compile todos os arquivos `.java` com o comando:
    ```bash
    javac *.java
    ```
4.  Execute a classe principal para iniciar o sistema:
    ```bash
    java Main
    ```
