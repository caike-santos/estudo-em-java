package aula14;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Endereço e1 = new Endereço("Centro", "Rua A", 123, "12345-678", "Casa");
        Endereço e2 = new Endereço("Bairro B", "Rua B", 456, "98765-432", "Apartamento");
        Endereço e3 = new Endereço("Bairro C", "Rua C", 789, "54321-987", "Casa");
        Endereço e4 = new Endereço("Bairro D", "Rua D", 321, "67890-123", "Apartamento");

        Telefone t1 = new Telefone("11", "98765-4321");
        Telefone t2 = new Telefone("22", "87654-3210");
        Telefone t3 = new Telefone("33", "76543-2109");
        Telefone t4 = new Telefone("44", "65432-1098");

        Atendente f1 = new Atendente("João", 30, "123.456.789-00", e1, t1, "joao@email.com", 1, 5000.0f, "Atendente");
        Medico f2 = new Medico("Maria", 40, "987.654.321-00", e2, t2, "maria@email.com", 2, 8000.0f, "Médico",
                "CRM-12345");
        Tosador f3 = new Tosador("Pedro", 35, "456.789.123-00", e3, t3, "pedro@email.com", 3, 6000.0f, "Tosador");

        Cliente c1 = new Cliente("Ana", 25, "321.654.987-00", e4, t4, "ana@email.com",
                new Pet("Rex", 10.0f, 5, 0.5f, Sexo.Macho, "Cachorro", "Pastor Alemão"));

        Cliente c2 = new Cliente("Carlos", 28, "654.321.987-00", e1, t1, "carlos@email.com",
                new Pet("Bolt", 15.0f, 3, 0.6f, Sexo.Macho, "Cachorro", "Golden Retriever"));       

        Cliente c3 = new Cliente("Mariana", 32, "789.123.456-00", e2, t2, "mariana@email.com",
                new Pet("Luna", 12.0f, 2, 0.5f, Sexo.Femea, "Gato", "Siamês"));

        f1.exibeDados();
        f2.exibeDados();
        f3.exibeDados();

        c1.exibeDados();
        c2.exibeDados();
        c3.exibeDados();

        List<Agenda> agendas = new ArrayList<>();

        f1.agendar(LocalDate.of(2024, 7, 1), LocalTime.of(14, 0), c1, Servicos.Vacinacao, f2, 150.0f, agendas);
        f1.agendar(LocalDate.of(2024, 7, 2), LocalTime.of(10, 0), c2, Servicos.Tosa, f3, 100.0f, agendas);
        f1.agendar(LocalDate.of(2024, 7, 3), LocalTime.of(16, 0), c3, Servicos.Banho, f3, 80.0f, agendas);

        f1.exibeAgendas(agendas);

        f1.exibeAgenda(agendas, agendas.get(1));

        f1.mudarDataHorario("Ana", agendas, LocalDate.of(2024, 7, 4), LocalTime.of(11, 0));
        f1.exibeAgenda(agendas, agendas.get(0));

        f1.deletarAgendamento("Ana", agendas);
        f1.exibeAgendas(agendas);

        Agenda agendaEncontrada = f1.pesquisaNome("Carlos", agendas);
        if (agendaEncontrada != null) {
            System.out.println("-----------------------------");
            System.out.println("Agendamento encontrado:");
            agendaEncontrada.exibirAgenda();
        } else {
            System.out.println("Agendamento não encontrado.");
        }
    }
}
