package aula14;

public class Medico extends Funcionario{
    private String cmrv;
    public Medico(String nome, int idade, String cpf, Endereço endereço, Telefone telefone, String email, int id, float salario, String funcao, String cmrv){
        super(nome, idade, cpf, endereço, telefone, email, id, salario, funcao);
        this.cmrv = cmrv;
    }
    public void setCmrv(String cmrv) {
        this.cmrv = cmrv;
    }
    public String getCmrv() {
        return cmrv;
    }

    @Override
    public void exibeDados(){
        super.exibeDados();
        System.out.println("CMRV: " + getCmrv());
    }
}
