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
    Medico f2 = new Medico("Maria", 40, "987.654.321-00", e2, t2, "maria@email.com", 2, 8000.0f, "Médico", "CRM-12345");
    Tosador f3 = new Tosador("Pedro", 35, "456.789.123-00", e3, t3, "pedro@email.com", 3, 6000.0f, "Tosador");

    Cliente c1 = new Cliente("Ana", 25, "321.654.987-00", e4, t4, "ana@email.com", new Pet("Rex", 10.0f, 5, 0.5f, Sexo.Macho, "Cachorro", "Pastor Alemão"));

    f1.exibeDados();
    f2.exibeDados();
    f3.exibeDados();

    c1.exibeDados();

    List<Agenda> agendas = new ArrayList<>();

    f1.agendar(LocalDate.of(2024, 7, 1), LocalTime.of(14, 0), c1, Servicos.Vacinacao, f2, 150.0f, agendas);
    f1.agendar(LocalDate.of(2024, 7, 2), LocalTime.of(10, 0), c1, Servicos.Tosa, f3, 100.0f, agendas);
    f1.agendar(LocalDate.of(2024, 7, 3), LocalTime.of(16, 0), c1, Servicos.Banho, f3, 80.0f, agendas);
    
    f1.exibeAgendas(agendas);
    
    f1.exibeAgenda(agendas, agendas.get(1));
    }
}
