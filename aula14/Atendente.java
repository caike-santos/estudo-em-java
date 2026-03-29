package aula14;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Atendente extends Funcionario{
    public Atendente(String nome, int idade, String cpf, Endereço endereço, Telefone telefone, String email, int id, float salario, String funcao) {
        super(nome, idade, cpf, endereço, telefone, email, id, salario, funcao);
    }

    public void agendar(LocalDate data, LocalTime hora, Cliente cliente, Servicos servico, Funcionario funcionario, float valor, List<Agenda> agendas){
        Agenda agenda = new Agenda(data, hora, cliente, servico, funcionario, valor, agendas);
        agenda.adicionarAgenda(agenda);
    } 
    
    public void exibeAgendas(List<Agenda> agendas){
        System.out.println("-----------------------------");
            System.out.println("Agenda:");
        for(Agenda agenda : agendas){
            System.out.println("-----------------------------");
            System.out.println("Agenda " + (agendas.indexOf(agenda) + 1) + ":");
            agenda.exibirAgenda();
        }
    }

    public void exibeAgenda(List<Agenda> agendas, Agenda agenda){
        System.out.println("-----------------------------");
        System.out.println("Agenda " + (agendas.indexOf(agenda) + 1) + ":");
        agenda.exibirAgenda();
    }
}
