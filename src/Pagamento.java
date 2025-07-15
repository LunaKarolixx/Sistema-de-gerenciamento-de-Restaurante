// Representa o pagamento de um pedido
public class Pagamento {
    private double valorTotal;
    private String formaPagamento;
    private boolean pago;

    public Pagamento(double valorTotal, String formaPagamento) {
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.pago = false;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public boolean isPago() {
        return pago;
    }

    public void realizarPagamento() {
        this.pago = true;
    }

    public void exibirPagamento() {
        System.out.println("Total: R$ " + valorTotal + 
                           " | Forma: " + formaPagamento + 
                           " | Pago: " + pago);
    }
}
