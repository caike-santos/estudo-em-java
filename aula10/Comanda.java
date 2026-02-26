package aula10;
import java.util.ArrayList;


public class Comanda {
    private int mesa;
    private ArrayList<ItemMenu> pedidos;

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
    public void setPedidos(ArrayList<ItemMenu> pedidos) {
        this.pedidos = pedidos;
    }
    public ArrayList<ItemMenu> getPedidos() {
        return pedidos;
    }

    public void adicionarItem(ItemMenu i){
        pedidos.add(i);
        System.out.println(i.getNome() + " foi adicionado à comanda da mesa "+ getMesa());
    }

    public double calcularPreço(){
        double precoFinal = 0.0;
        for(ItemMenu item : pedidos){
            precoFinal += item.getPreco();
        }
        return precoFinal;
    }

    public void exibirRecibo(){
        System.out.println("---------Recibo---------");
        for(ItemMenu item : pedidos){
            item.exibirDetalhes();
        }
        System.out.println("------------------------");
        System.out.printf("Valor total: R$ %.2f", calcularPreço());
    }
}
