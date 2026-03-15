package aula13;

public class Comanda implements Imprimivel{
    private int numeroMesa;
    private double valorTotal;

    public Comanda(int n, double v){
        this.numeroMesa = n;
        this.valorTotal = v;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public void mostrarNaTela(){
        System.out.println("Comanda da mesa: "+ this.getNumeroMesa());
        System.out.println("Valor total: "+ getValorTotal());
        System.out.println("----------------------------------");
    }
}
