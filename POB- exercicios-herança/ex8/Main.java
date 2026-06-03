package ex8;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria("jose", 500);

        try{
            conta.sacar(600);
        }catch(SaldoInsuficienteException e){
            System.err.println("ERRO AO REALIZAR O SAQUE");
            System.err.println(e.getMessage());
        }
    }
}
