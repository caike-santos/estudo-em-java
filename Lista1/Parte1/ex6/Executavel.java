package ex6;

import java.util.ArrayList;

public class Executavel {
    public static void main(String[] args) {
        Funcionario g1 = new Gerente("Geraldo", 8045.45, 758.75);
        Funcionario d1 = new Desenvolvedor("Alana", 5470.65, 85);
        ArrayList<Funcionario> f = new ArrayList<>();

        f.add(d1);
        f.add(g1);
        

        for (Funcionario func : f) {
            System.out.println("\n");
            func.exibir();
        }
    }
}
