package ex3;

public abstract class Conta {
    
    private String dono;
    private double saldo;

    public Conta(String d, double s){
        dono = d;
        saldo = s;
    }

    public String getDono() {
        return dono;
    }
    public void setDono(String dono) {
        this.dono = dono;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    

    public void exibirDados(){
        System.out.println("Dono: " + getDono());
        System.out.println("Saldo: " + getSaldo());
    }

    public abstract double saque(int x);
}
