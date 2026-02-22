package aula09;

public class Prato {
    private String nome;
    private double preco;

    public Prato(String n, double p){
        this.nome = n;
        this.preco = p;
    }

    public void exibirDetalhes(){
        System.out.printf("%s custa: R$%.2f\n",this.nome, this.preco );
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
