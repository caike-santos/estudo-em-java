package aula14;

public class Endereço {
    private String bairro;
    private String rua;
    private int numero;
    private String cep;
    private String complemento;

    public Endereço(String bairro, String rua, int numero, String cep, String complemento){
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
        this.rua = rua;
        this.complemento = complemento;

    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    
}
