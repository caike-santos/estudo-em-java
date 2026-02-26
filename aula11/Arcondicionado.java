package aula11;

public class Arcondicionado extends Dispositivos {
    private double temperatura;

    public Arcondicionado(double t, String n, double c){
        super(n, c);
        this.temperatura = t;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
    public double getTemperatura() {
        return temperatura;
    }

    public void exibirDetalhes(){
        System.out.printf("Nome: %s\n", this.getNome());
        System.out.printf("Consumowatts: %s Watts\n", this.getConsumoWatts());
        System.out.printf("Temperatura minima: %.2f\n", this.getTemperatura());
        if(getLigado()){
            System.out.println("Dispositivo: Ligado");
        }else{
            System.out.println("Dispositivo desligado");
        }
    }
}
