package ex5;

public class Cliente implements Autenticavel{
    @Override
    public void exibir() {
        System.out.println("Autenticando cliente...");
    }
}
