package ex7;

import java.util.ArrayList;

public class CarrinhoDeCompras {
    private ArrayList<Produto> carrinho;

    public CarrinhoDeCompras(){
        carrinho = new ArrayList<>();
    }

    public void adicionarProduto(Produto p){
        carrinho.add(p);
    }

    public double calcularTotal(){
        double total = 0.0;
        for(Produto pro : carrinho){
            total += pro.getPreco();
        }
        return total;
    }

    public void aplicarDescontoGeral(double percentual){
        for(Produto pro : carrinho){
            pro.aplicarDesconto(percentual);
        }
    }
}
