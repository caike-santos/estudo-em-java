package ex4;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Animal> animais = new ArrayList<>();
        Cachorro c = new Cachorro("Rex");
        Gato g = new Gato("Mingau");

        animais.add(c);
        animais.add(g);

        for (Animal animal : animais) {
            animal.fazerSom();
        }
    }
}
    
