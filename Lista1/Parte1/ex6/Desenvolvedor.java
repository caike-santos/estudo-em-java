package ex6;

public class Desenvolvedor extends Funcionario{
    private int horasExtras;

    public Desenvolvedor(String nome, double s, int h){
        super(nome, s);
        horasExtras = h;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }
    public int getHorasExtras() {
        return horasExtras;
    }

    public double calcularSalario(){
        return getSalarioBase() + (horasExtras*50);
    }
    @Override
    public void exibir(){
        super.exibir();
        System.out.println("Horas extras: " + getHorasExtras());
    }

}
