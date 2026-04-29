package ex4;

public abstract class Animal {
    private String nome;
    public Animal(String n){
        nome = n;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public abstract void fazerSom();
}
