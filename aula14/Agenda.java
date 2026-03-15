package aula14;

public class Agenda {
    private Data data;
    private Horario hora;
    private Cliente cliente;
    private Serviços servico;
    private Medico medico;
    private Tosador tosador;
    private float valor;

    public Agenda(Data data, Cliente cliente, Serviços servico){
        this.cliente = cliente;
        this.data = data;
        this.servico = servico;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }
    public void setHora(Horario hora) {
        this.hora = hora;
    }
    public Horario getHora() {
        return hora;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public Medico getMedico() {
        return medico;
    }
    public void setServico(Serviços servico) {
        this.servico = servico;
    }
    public Serviços getServico() {
        return servico;
    }
    public void setTosador(Tosador tosador) {
        this.tosador = tosador;
    }
    public Tosador getTosador() {
        return tosador;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public float getValor() {
        return valor;
    }
}
