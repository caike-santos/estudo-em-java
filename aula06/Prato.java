package aula06;

public class Prato {
    public String nome;
    public double preco;

    public Prato(String n, double p){
        this.nome = n;
        this.preco = p;
    }

    public void exibirDetalhes(){
        System.out.printf("%s custa: R$%.2f\n",this.nome, this.preco );
    }
}
