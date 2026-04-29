package ex4;

public class Gato extends Animal{
    public Gato(String n){
        super(n);
    }

    @Override
    public void fazerSom(){
        System.out.println("MIAUUUUU");
    }
}
