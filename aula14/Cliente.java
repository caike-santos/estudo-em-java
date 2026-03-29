package aula14;

public class Cliente extends Pessoa{
    private Pet pet;
    public Cliente(String nome, int idade, String cpf, Endereço endereço, Telefone telefone, String email, Pet pet){
        super(nome, idade, cpf, endereço, telefone, email);
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
        super.exibeDados();
        System.out.println("Pet: " + getPet().getNome());
    }
}
