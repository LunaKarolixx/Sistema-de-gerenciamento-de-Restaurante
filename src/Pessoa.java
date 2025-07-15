// Classe abstrata base que define atributos e comportamento comum para Cliente e Garçom
public abstract class Pessoa {
    protected String nome; 

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void exibirDados();
}
