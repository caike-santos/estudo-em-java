package aula10;


public class Caixa {
    public static void main(String[] args) {
        Prato p1 = new Prato("Arroz com Batata", 20.50);
        Prato p2 = new Prato("Feijoada", 30.00);
        Prato p3 = new Prato("Salada", 15.00);

        Bebida b1 = new Bebida("Suco de uva", 10.00, 250);
        Bebida b2 = new Bebida("Coca cola", 15.00, 1000);

        Comanda mesa5 = new Comanda(5);

        mesa5.adicionarItem(p1);
        mesa5.adicionarItem(p2);
        mesa5.adicionarItem(p3);
        mesa5.adicionarItem(b1);
        mesa5.adicionarItem(b2);

        mesa5.exibirRecibo();
    }
}
