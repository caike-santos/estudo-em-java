package aula07;

public class Index {
    public static void main(String[] args) {
        Caneta c1 = new Caneta("preta", "plastico", true);
        Caneta c2 = new Caneta("azul", "madeira", false);

        c2.detalhes();
        
        
        c2.tampar();
        c2.detalhes();
    }
    
}
