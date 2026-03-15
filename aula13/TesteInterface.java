package aula13;

public class TesteInterface {
    
    public static void main(String[] args) {
        
        // Criando objetos totalmente diferentes
        Comanda c = new Comanda(10, 150.00);
        Holerite h = new Holerite("João Garçom", 2500.00);

        // A mesma impressora aceita os dois!
        Impressora.imprimir(c);
        Impressora.imprimir(h);
    }
}

