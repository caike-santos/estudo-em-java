package ex6;

public class Gerente extends Funcionario {
    private double bonus;

    public Gerente(String n, double s, double b){
        super(n, s);
        bonus = b;
    }
    public double getBonus() {
        return bonus;
    }
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double calcularSalario(){
        return this.getSalarioBase() + bonus;
    }

    @Override
    public void exibir(){
        super.exibir();
        System.out.println("Bonus: " + getBonus());
    }
}
