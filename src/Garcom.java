public class Garcom extends Pessoa {
    private int id; // Atributo exclusivo do garçom

    public Garcom(String nome, int id) {
        super(nome);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void exibirDados() {
        System.out.println("Garçom: " + nome + " | ID: " + id);
    }
}
