package aula11;

public class CasaInteligente {
    public static void main(String[] args) {
        
        // 1. Criando os cômodos
        Comodo sala = new Comodo("Sala de Estar");

        // 2. Criando os dispositivos
        Lampada luzTeto = new Lampada( "Branca","Luz do Teto", 15.0);
        Arcondicionado arSala = new Arcondicionado(22,"Ar Split", 1500.0);

        // 3. Adicionando no cômodo
        sala.addAparelho(luzTeto);
        sala.addAparelho(arSala);

        // 4. Teste 1: Tudo desligado
        sala.dispositivos();

        // 5. Teste 2: Ligando apenas o Ar Condicionado
        arSala.ligar();
        sala.dispositivos();
    }
}
