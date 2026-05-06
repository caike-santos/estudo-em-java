package dominio;
public class Vendedor extends Funcionario{
    private double comissao;

    public Vendedor(String c, double s, double co){
        super(c, s);
        comissao = co;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
    public double getComissao() {
        return comissao;
    }
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("Comissão: " +getComissao());
    }
}
