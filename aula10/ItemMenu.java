package aula10;

public abstract class ItemMenu {
    String nome;
    double preco;

    public ItemMenu(String n, double p){
        this.nome = n;
        this.preco = p;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getPreco() {
        return preco;
    }

    public void exibirDetalhes(){
       System.out.printf("%s custa: R$%.2f\n",this.nome, this.preco );
    }
}
