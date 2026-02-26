package aula11;

public abstract class Dispositivos {
    private String nome;
    private double consumoWatts;
    private boolean ligado;

    public Dispositivos(String n, double c){
        this.nome = n;
        this.consumoWatts = c;
        this.ligado = false;
    }

    public void setConsumoWatts(double consumoWatts) {
        this.consumoWatts = consumoWatts;
    }
    public double getConsumoWatts() {
        return consumoWatts;
    }
    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }
    public boolean getLigado() {
        return ligado;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void ligar(){
        if(!ligado){
            this.ligado = true; 
    }
    }
    public void desligar(){
        if(ligado){
            this.ligado = false;
        }
    }
    public abstract void exibirDetalhes();
}
