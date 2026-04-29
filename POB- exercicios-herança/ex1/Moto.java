package ex1;

public class Moto extends Veiculo {
    protected float cilindradas;

    public Moto(String marca, String modelo, float cilindradas){
        super(marca, modelo);
        this.cilindradas = cilindradas;
    }

    @Override
    public void exibirDados(){
        super.exibirDados();
        System.out.printf("Cilindradas: %.2f");
    }
}
