import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
    private Cliente cliente;
    private Garcom garcom;
    private Map<Produto, Integer> itens = new HashMap<>(); 
    private Pagamento pagamento;

    public Pedido(Cliente cliente, Garcom garcom) {
        this.cliente = cliente;
        this.garcom = garcom;
    }

 
    public void adicionarProduto(Produto p) {
        itens.merge(p, 1, Integer::sum);
    }
    
    public void removerProduto(Produto p, int quantidadeParaRemover) {
        if (itens.containsKey(p)) {
            int quantidadeAtual = itens.get(p);
            if (quantidadeParaRemover >= quantidadeAtual) {
                itens.remove(p);
            } else {
                itens.put(p, quantidadeAtual - quantidadeParaRemover);
            }
        }
    }

    public Map<Produto, Integer> getItens() {
        return itens;
    }

    
    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            total += entry.getKey().getPreco() * entry.getValue();
        }
        return total;
    }

    public double calcularTotalComTaxaServico() {
        return calcularTotal() * 1.10;
    }
    
    public void realizarPagamento(String formaPagamento) {
        if (this.pagamento == null) {
            this.pagamento = new Pagamento(calcularTotalComTaxaServico(), formaPagamento);
        }
        this.pagamento.realizarPagamento();
    }

    public boolean estaPago() {
        return pagamento != null && pagamento.isPago();
    }

    public Garcom getGarcom() {
        return garcom;
    }
    
    
    public void exibirPedido() {
        System.out.println("--- Detalhes do Pedido ---");
        cliente.exibirDados();
        garcom.exibirDados();
        System.out.println("Itens:");
        if (itens.isEmpty()) {
            System.out.println("  - (Nenhum item no pedido)");
        } else {
            for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
                Produto produto = entry.getKey();
                int quantidade = entry.getValue();
                System.out.println("  - " + produto.getNome() + " (x" + quantidade + ") - R$ " + String.format("%.2f", produto.getPreco() * quantidade));
            }
        }
        if (pagamento != null) {
            pagamento.exibirPagamento();
        }
        System.out.println("-------------------------");
    }
}
