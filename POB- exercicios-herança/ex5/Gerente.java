package ex5;

public class Gerente extends Funcionario{
    
    private String departamento;

    public Gerente(String n, double s, String d){
        super(n, s);
        departamento = d;
    }

    public double bonus(){
        if(getDepartamento().equals("teste1")){
            setSalario(getSalario() * 1.1);
        }else if(getDepartamento().equals("teste2")){
            setSalario(getSalario() * 1.2);
        }
        return getSalario();
    }

    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
        
        
    
}
