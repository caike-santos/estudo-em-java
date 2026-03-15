package aula14;

public class Cliente extends Pessoa{
    private Pet pet;
    public Cliente(Pet pet){
        super();
        this.pet = pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    public Pet getPet() {
        return pet;
    }

    @Override
    public void exibeDados(){
        
    }
}
