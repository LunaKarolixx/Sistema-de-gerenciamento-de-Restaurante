classDiagram
    class Pessoa {
        <<abstract>>
        #String nome
        +getNome() String
        +exibirDados()
    }
    class Cliente {
        -String cpf
        +getCpf() String
        +exibirDados()
    }
    class Garcon {
        -int id
        +getId() int
        +exibirDados()
    }
    class Produto {
        -String nome
        -double preco
        -String setor
    }
    class Mesa {
        -int numero
        -Cliente clienteOcupante
        +ocuparComCliente(Cliente)
        +limparMesa()
        +estaOcupada() bool
    }
    class Pedido {
        -Map~Produto, Integer~ itens
        +adicionarProduto(Produto)
        +removerProduto(Produto, int)
        +calcularTotal() double
    }
    class Pagamento {
        -double valorTotal
        -boolean pago
        +realizarPagamento()
    }
    class Main {
        -List~Produto~ produtos
        -List~Cliente~ clientes
        -List~Garcon~ garcons
        -List~Mesa~ mesas
        +main(String[])
    }

    Pessoa <|-- Cliente
    Pessoa <|-- Garcon

    Mesa "1" -- "1" Pedido : contém
    Pedido "1" *-- "1" Pagamento : gera
    
    Mesa "1" o-- "1" Cliente : ocupada por
    Pedido "*" o-- "1" Cliente : feito por
    Pedido "*" o-- "1" Garcon : atendido por
    Pedido "*" -- "*" Produto : contém

    Main ..> Pessoa : gerencia
    Main ..> Mesa : gerencia
    Main ..> Produto : gerencia

