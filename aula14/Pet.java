package aula14;

public class Pet{
    private String nome;
    private float peso;
    private int idade;
    private float altura;
    private Sexo sexo;
    private String especie;
    private String raca;

    public Pet(String nome, float peso, int idade, float altura, Sexo sexo, String especie, String raca){
        this.altura = altura;
        this.idade = idade;
        this.nome = nome;
        this.peso = peso;
        this.sexo = sexo;
        this.especie = especie;
        this.raca = raca;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
    public float getAltura() {
        return altura;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public int getIdade() {
        return idade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }
    public float getPeso() {
        return peso;
    }
    public void setSexo(Sexo sexo) {
            this.sexo = sexo;
    }
    public Sexo getSexo() {
        return sexo;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getEspecie() {
        return especie;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }
    public String getRaca() {
        return raca;
    }

    public void serBanhado(){
            System.out.println("Banho tomado");
    }

    public void serTosado(){
        System.out.println("Tosado");
    }

    public void serChecado(){
        System.out.println("Check-Up completo :)");
    }

    public void serVacinado(){
        System.out.println("Vacinado com sucesso");
    }

    public void serInternado(){
        System.out.println("Internaçao aceita, venha buscar seu pet no dia seguinte");
    }

    public void serOperado(){
        System.out.println("Cirurgia bem sucedida");
    }

    public void exibePet(){
        System.out.println("Nome: " + getNome());
        System.out.printf("Peso: %.2f\n", getPeso());
        System.out.println("Idade: " + getIdade());
        System.out.printf("Altura: %.2f\n", getAltura());
        System.out.println("Sexo: " + getSexo());
        System.out.println("Especie: " + getEspecie());
        System.out.println("Raça: "+ getRaca());
    }

}
