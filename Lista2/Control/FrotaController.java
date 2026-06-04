package Lista2.Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Lista2.Model.Veiculo;
import Lista2.Repository.FrotaRepository;

public class FrotaController {
    private List<Veiculo> frota = new ArrayList<>();
    private final FrotaRepository repository = new FrotaRepository();

    public void adicionarVeiculo(Veiculo v) {
        if (v == null) {
            throw new IllegalArgumentException("O veículo não pode ser nulo.");
        }
        frota.add(v);
    }

    // Retorna uma cópia para proteger a integridade da lista original
    public List<Veiculo> getFrota() {
        return new ArrayList<>(this.frota);
    }

    public void salvarFrota(String nomeArquivo) throws IOException {
        repository.salvar(this.frota, nomeArquivo);
    }

    public void carregarFrota(String nomeArquivo) throws IOException {
        this.frota = repository.ler(nomeArquivo);
    }
}
