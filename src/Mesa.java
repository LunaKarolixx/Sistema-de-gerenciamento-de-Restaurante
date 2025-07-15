import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int numero;
    private Cliente clienteOcupante;
    private List<Pedido> pedidos = new ArrayList<>();

    public Mesa(int numero) {
        this.numero = numero;
        this.clienteOcupante = null;
    }

    public int getNumero() {
        return numero;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
    
    public void ocuparComCliente(Cliente cliente) {
        if (!estaOcupada()) {
            this.clienteOcupante = cliente;
        }
    }

    public boolean estaOcupada() {
        return !getPedidosEmAberto().isEmpty();
    }

    public List<Pedido> getPedidosEmAberto() {
        List<Pedido> pedidosAbertos = new ArrayList<>();
        for (Pedido pedido : this.pedidos) {
            if (!pedido.estaPago()) {
                pedidosAbertos.add(pedido);
            }
        }
        return pedidosAbertos;
    }

    public void limparMesa() {
        this.pedidos.removeIf(Pedido::estaPago);
        if (!estaOcupada()) {
            this.clienteOcupante = null;
        }
    }

    public void exibirStatusMesa() {
        System.out.print("Mesa " + numero + " - ");
        if (estaOcupada()) {
            Garcom garcom = getPedidosEmAberto().get(0).getGarcom();
            System.out.print("Ocupada | Cliente: " + clienteOcupante.getNome() + " | Garçom: " + garcom.getNome());
        } else {
            System.out.print("Livre");
        }
        System.out.println();
    }
}
