package ex3;

public class ContaCorrente extends Conta{
    public ContaCorrente(String d, double s){
       super(d, s);
    }

    @Override
    public double saque(int x){
        double v = getSaldo() - x;
        if(v < 0){
           
            v *= 1.08;
            setSaldo(v);
            System.out.println("Entrou em cheque especial com um valor de v");

        }
        return v;
    }

    @Override
    public void exibirDados(){
        super.exibirDados();
        System.out.println("Tipo da conta: Conta Poupança");
    }
}
