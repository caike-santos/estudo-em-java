package aula10;

public class Bebida extends ItemMenu{
    private int ml;

    public Bebida(String n, double p, int ml){
        super(n, p);
        this.ml = ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }
    public double getMl() {
        return ml;
    }

    @Override
    public void exibirDetalhes(){
        System.out.printf("%d ml de %s custa: R$%.2f\n",this.ml, this.nome, this.preco );
    }

}
