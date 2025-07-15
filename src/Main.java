import java.util.*;

public class Main {
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Garcom> garcons = new ArrayList<>();
    private static List<Mesa> mesas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== BEM-VINDO AO SISTEMA COMANDE AQUI =====");

        int quantidadeMesas = 0;
        while (quantidadeMesas <= 0) {
            try {
                System.out.print("Informe o número de mesas do restaurante: ");
                quantidadeMesas = scanner.nextInt();
                if (quantidadeMesas <= 0) {
                    System.out.println("Por favor, insira um número positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }
        scanner.nextLine(); 

        for (int i = 1; i <= quantidadeMesas; i++) {
            mesas.add(new Mesa(i));
        }

        int opcao;
        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1 -> cadastrarProduto();
                    case 2 -> cadastrarCliente();
                    case 3 -> cadastrarGarcom();
                    case 4 -> criarPedido();
                    case 5 -> visualizarMesas();
                    case 6 -> realizarPagamento();
                    case 7 -> listarProdutos();
                    case 8 -> gerenciarItensDePedido();
                    case 0 -> System.out.println("Encerrando sistema...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Opção inválida. Por favor, digite um número do menu.");
                scanner.nextLine(); 
                opcao = -1; 
            }

        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Cadastrar Cliente");
        System.out.println("3. Cadastrar Garçom");
        System.out.println("4. Criar ou Adicionar a Pedido");
        System.out.println("5. Visualizar Mesas");
        System.out.println("6. Realizar Pagamento / Fechar Conta");
        System.out.println("7. Listar Produtos");
        System.out.println("8. Gerenciar Itens de Pedido");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProduto() {
        try {
            System.out.print("Nome do produto: ");
            String nome = scanner.nextLine();
            System.out.print("Preço do produto: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Setor de preparo (Cozinha, Bar, Confeitaria): ");
            String setor = scanner.nextLine();
            produtos.add(new Produto(nome, preco, setor));
            System.out.println("Produto cadastrado!");
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada. O preço deve ser um número. Tente novamente.");
            scanner.nextLine(); 
        }
    }

    private static void cadastrarCliente() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        clientes.add(new Cliente(nome, cpf));
        System.out.println("Cliente cadastrado!");
    }

    private static void cadastrarGarcom() {
        try {
            System.out.print("Nome do garçom: ");
            String nome = scanner.nextLine();
            System.out.print("ID do garçom: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            garcons.add(new Garcom(nome, id));
            System.out.println("Garçom cadastrado!");
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada. O ID deve ser um número. Tente novamente.");
            scanner.nextLine();
        }
    }

    private static void criarPedido() {
        if (clientes.isEmpty() || garcons.isEmpty() || produtos.isEmpty()) {
            System.out.println("É necessário ter cliente, garçom e produto cadastrados.");
            return;
        }

        try {
            System.out.println("Escolha o cliente:");
            for (int i = 0; i < clientes.size(); i++) System.out.println(i + " - " + clientes.get(i).getNome());
            int indiceCliente = scanner.nextInt();
            Cliente cliente = clientes.get(indiceCliente);

            System.out.println("Escolha o garçom:");
            for (int i = 0; i < garcons.size(); i++) System.out.println(i + " - " + garcons.get(i).getNome());
            int indiceGarcom = scanner.nextInt();
            Garcom garcom = garcons.get(indiceGarcom);

            System.out.println("Escolha a mesa:");
            for (Mesa m : mesas) m.exibirStatusMesa();
            System.out.print("Número da mesa: ");
            int numeroMesa = scanner.nextInt();
            Mesa mesa = mesas.get(numeroMesa - 1);

            mesa.ocuparComCliente(cliente);

            Pedido pedido;
            List<Pedido> pedidosAbertos = mesa.getPedidosEmAberto();

            if (pedidosAbertos.isEmpty()) {
                pedido = new Pedido(cliente, garcom);
                mesa.adicionarPedido(pedido);
            } else {
                pedido = pedidosAbertos.get(0); 
            }

            System.out.println("Adicionando itens... Escolha os produtos (digite -1 para encerrar):");
            listarProdutos();
            int escolha;
            do {
                System.out.print("Produto #: ");
                escolha = scanner.nextInt();
                if (escolha >= 0 && escolha < produtos.size()) {
                    pedido.adicionarProduto(produtos.get(escolha));
                }
            } while (escolha != -1);

            System.out.println("Pedido atualizado com sucesso na mesa " + numeroMesa + "!");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Seleção inválida. Um dos itens escolhidos não existe. Operação cancelada.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, use apenas números para selecionar. Operação cancelada.");
            scanner.nextLine();
        }
    }

    private static void realizarPagamento() {
        try {
            System.out.print("Informe o número da mesa para fechar a conta: ");
            int numMesa = scanner.nextInt();
            scanner.nextLine();
            Mesa mesa = mesas.get(numMesa - 1);

            if (!mesa.estaOcupada()) {
                System.out.println("Esta mesa não possui uma conta em aberto.");
                return;
            }

            double valorTotalConta = 0;
            System.out.println("\n===== Resumo da Conta da Mesa " + mesa.getNumero() + " =====");
            for (Pedido pedido : mesa.getPedidosEmAberto()) {
                pedido.exibirPedido();
                valorTotalConta += pedido.calcularTotalComTaxaServico();
            }
            System.out.println("----------------------------------------");
            System.out.println("VALOR TOTAL (com taxa): R$ " + String.format("%.2f", valorTotalConta));
            System.out.println("----------------------------------------");

            System.out.print("Forma de pagamento (Crédito, Débito, Dinheiro, Pix): ");
            String forma = scanner.nextLine();

            for (Pedido pedido : mesa.getPedidosEmAberto()) {
                pedido.realizarPagamento(forma);
            }

            mesa.limparMesa();

            System.out.println("Pagamento realizado com sucesso! A mesa " + mesa.getNumero() + " está livre.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro: Mesa não encontrada.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número de mesa válido.");
            scanner.nextLine();
        }
    }

    private static void gerenciarItensDePedido() {
        try {
            System.out.print("Informe o número da mesa: ");
            int numMesa = scanner.nextInt();
            Mesa mesa = mesas.get(numMesa - 1);

            if (!mesa.estaOcupada()) {
                System.out.println("Nenhum pedido em aberto encontrado para essa mesa.");
                return;
            }

            Pedido pedidoParaEditar = mesa.getPedidosEmAberto().get(0);
            Map<Produto, Integer> itens = pedidoParaEditar.getItens();
            if (itens.isEmpty()) {
                System.out.println("O pedido não tem produtos para gerenciar.");
                return;
            }

            List<Produto> listaDeProdutosDoPedido = new ArrayList<>(itens.keySet());

            System.out.println("Itens do pedido na Mesa " + numMesa + ":");
            for (int i = 0; i < listaDeProdutosDoPedido.size(); i++) {
                Produto p = listaDeProdutosDoPedido.get(i);
                System.out.println(i + " - " + p.getNome() + " (x" + itens.get(p) + ")");
            }

            System.out.print("Digite o índice do produto a gerenciar: ");
            int index = scanner.nextInt();

            Produto produtoSelecionado = listaDeProdutosDoPedido.get(index);

            System.out.println("O que você deseja fazer com " + produtoSelecionado.getNome() + "?");
            System.out.println("1. Remover 1 unidade");
            System.out.println("2. Remover todas as unidades (" + itens.get(produtoSelecionado) + ")");
            System.out.print("Opção: ");
            int acao = scanner.nextInt();

            if (acao == 1) {
                pedidoParaEditar.removerProduto(produtoSelecionado, 1);
                System.out.println("1 unidade de " + produtoSelecionado.getNome() + " removida.");
            } else if (acao == 2) {
                // Para remover todos, passamos a quantidade atual
                pedidoParaEditar.removerProduto(produtoSelecionado, itens.get(produtoSelecionado));
                System.out.println("Todos as unidades de " + produtoSelecionado.getNome() + " foram removidas.");
            } else {
                System.out.println("Ação inválida.");
            }

        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println("Entrada ou seleção inválida. Tente novamente.");
            scanner.nextLine();
        }
    }

    private static void visualizarMesas() {
        System.out.println("\nStatus das Mesas:");
        if (mesas.isEmpty()) {
            System.out.println("Nenhuma mesa cadastrada.");
        }
        for (Mesa m : mesas) {
            m.exibirStatusMesa();
        }
    }

    private static void listarProdutos() {
        System.out.println("\n--- Cardápio ---");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                System.out.print(i + " - ");
                produtos.get(i).exibirProduto();
            }
        }
    }
}
