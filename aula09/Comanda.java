package aula09;
import java.util.ArrayList;
public class Comanda {
    private int mesa;
    private ArrayList<Prato> pedidos;

    public Comanda(int m){
        this.mesa = m;
        this.pedidos = new ArrayList<>();
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
    public int getMesa() {
        return mesa;
    }
    public void setPedidos(ArrayList<Prato> pedidos) {
        this.pedidos = pedidos;
    }
    public ArrayList<Prato> getPedidos() {
        return pedidos;
    }

    public void adicionarPrato(Prato p){
        pedidos.add(p);
        System.out.println(p.getNome() + " foi adicionado à comanda da mesa "+ getMesa());
    }

    public double calcularPreço(){
        double precoFinal = 0.0;
        for(Prato item : pedidos){
            precoFinal += item.getPreco();
        }
        return precoFinal;
    }

    public void exibirRecibo(){
        System.out.println("---------Recibo---------");
        for(Prato item : pedidos){
            item.exibirDetalhes();
        }
        System.out.println("------------------------");
        System.out.println("Valor total: "+ calcularPreço());
    }
}
