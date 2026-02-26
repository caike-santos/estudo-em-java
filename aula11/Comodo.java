package aula11;
import java.util.ArrayList;

public class Comodo {
    private String nome;
    private ArrayList<Dispositivos> aparelhos;

    public Comodo(String nome){
        this.nome = nome;
        this.aparelhos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public ArrayList<Dispositivos> getAparelhos() {
        return aparelhos;
    }
    public void setAparelhos(ArrayList<Dispositivos> aparelhos) {
        this.aparelhos = aparelhos;
    }

    public void addAparelho(Dispositivos d){
        this.aparelhos.add(d);
    }

    public double consumo(){
        double total = 0;
        for(Dispositivos item : aparelhos){
            if(item.getLigado()){
                total += item.getConsumoWatts();
            }
        }
        return total;
    }

    public void dispositivos(){
        System.out.printf("Dispositivos no comodo: %s\n\n", this.getNome());
        for(Dispositivos item : aparelhos){
            item.exibirDetalhes();
            System.out.printf("\n\n");
        }
        System.out.printf("Consumo total: %.2f Watts", this.consumo());
        System.out.printf("\n------------------------------\n");
    }
}

