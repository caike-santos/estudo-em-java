package dominio;

public class Diretor extends Funcionario{

    private double bonificacao;
    
    public Diretor(String c, double s, double b){
        super(c, s);
        bonificacao = b;
    }

    public void setBonificacao(double bonificacao) {
        this.bonificacao = bonificacao;
    }
    public double getBonificacao() {
        return bonificacao;
    }
   
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("Bonificação: " + getBonificacao());
    }
}