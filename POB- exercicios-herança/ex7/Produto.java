package ex7;

public class Produto implements Promocional{
    private String nome;
    private double preco;

    public Produto(String n, double p){
        nome = n;
        preco = p;
    }

    public void aplicarDesconto(double percentual){
        preco -= preco * (percentual/100);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
}
