// Produto.java
import java.util.Objects;

public class Produto {
    private String nome;
    private double preco;
    private String setor;

    public Produto(String nome, double preco, String setor) {
        this.nome = nome;
        this.preco = preco;
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
    
    public String getSetor() {
        return setor;
    }

    public void exibirProduto() {
        System.out.println(nome + " (" + setor + ") - R$ " + String.format("%.2f", preco));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(produto.preco, preco) == 0 && Objects.equals(nome, produto.nome) && Objects.equals(setor, produto.setor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco, setor);
    }
}
