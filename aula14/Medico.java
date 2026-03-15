package aula14;

public class Medico extends Funcionario{
    private String cmrv;
    public Medico(String cmrv){
        super();
        this.cmrv = cmrv;
    }
    public void setCmrv(String cmrv) {
        this.cmrv = cmrv;
    }
    public String getCmrv() {
        return cmrv;
    }
}
