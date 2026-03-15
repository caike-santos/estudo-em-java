package aula14;

public class Telefone {
    private String ddd;
    private String numero;

    public Telefone(String ddd, String numero){
        this.ddd = ddd;
        this.numero = numero;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
    public String getDdd() {
        return ddd;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getNumero() {
        return numero;
    }
    
}
