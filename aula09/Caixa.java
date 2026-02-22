package aula09;

public class Caixa {
    public static void main(String[] args) {
        Prato p1 = new Prato("Arroz com Batata", 20.50);
        Prato p2 = new Prato("Feijoada", 30.00);
        Prato p3 = new Prato("Salada", 15.00);

        Comanda mesa5 = new Comanda(5);

        mesa5.adicionarPrato(p1);
        mesa5.adicionarPrato(p2);
        mesa5.adicionarPrato(p2);

        mesa5.exibirRecibo();
    }
}
