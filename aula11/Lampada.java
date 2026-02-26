package aula11;

public class Lampada extends Dispositivos{
    private String cor;

    public Lampada(String c, String n, double con){
        super(n, con);
        this.cor = c;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getCor() {
        return cor;
    }

    public void exibirDetalhes(){
        System.out.printf("Nome: %s\n", this.getNome());
        System.out.printf("Consumowatts: %s Watts\n", this.getConsumoWatts());
        System.out.printf("Cor da lampada: %s\n", getCor());
        if(getLigado()){
            System.out.println("Dispositivo: Ligado");
        }else{
            System.out.println("Dispositivo desligado");
        }
    }

}
