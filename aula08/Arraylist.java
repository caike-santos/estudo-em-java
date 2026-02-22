package aula08;
import java.util.ArrayList;
import aula07.Caneta;

public class Arraylist {
    public static void main(String[] args){
    Caneta c1 = new Caneta("vermelha", "papel", true);
    Caneta c2 = new Caneta("verde", "latao", false);

    ArrayList<Caneta>estojo = new ArrayList<>();

    estojo.add(c1);
    estojo.add(c2);
    estojo.add(new Caneta("amarela", "tecido", false));

    System.out.println("Tamanho do estojo: " + estojo.size());

    for(Caneta item : estojo){
        item.detalhes();
        System.out.printf("\n\n");
    }
    }
}
