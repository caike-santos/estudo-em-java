package aula06;

public class TestePoo {
    public static void main(String[] args) {
        Prato prato1 = new Prato("Arroz com Batata", 25.50);
        Prato prato2 = new Prato("Carne de Panela", 36.65);

        prato1.exibirDetalhes();
        prato2.exibirDetalhes();
    }
}
