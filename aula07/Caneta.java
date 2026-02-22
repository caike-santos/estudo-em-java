package aula07;

public class Caneta {
    private String cor;
    private String material;
    private boolean tampada;
    private boolean tampa;

    public Caneta(String c, String m, boolean t){
    this.cor = c;
    this.material = m;
    this.tampa = t;
    if(this.tampa){
    this.tampada = true;
    }else{
        this.tampada = false;
    }
    
    }

    public String getCor() {
        return this.cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMaterial() {
        return this.material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public boolean getTampada(){
        return this.tampada;
    }
    public void setTampada(boolean tampada){
        if(tampa){
        this.tampada = tampada;
        }else{
            System.out.println("Sua caneta não possui tampa");
        }
    }
    public boolean getTampa(){
        return this.tampa;
    }
    public void setTampa(boolean tampa) {
        this.tampa = tampa;
    }

    public void detalhes(){
        System.out.println("Cor: " + this.cor);
        System.out.println("Material: " + this.material);
        if(tampa){
            System.out.println("Possui tampa");
            if(tampada){
                System.out.println("Está tampada");
            }else{
                System.out.println("Está destampada");
            }
        }else{
            System.out.println("Nao possui tampa");
        }
        
    }

    public void tampar(){
        if(tampada){
            System.out.println("Sua caneta já está tampada");
        }else{
            setTampada(true);
        }
    }

    public void destampar(){
        if(tampada){
            setTampada(false);
        }else{
            System.out.println("Sua caneta já está destampada");
        }
    }
}


