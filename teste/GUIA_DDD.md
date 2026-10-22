# Guia Prático de DDD (Domain-Driven Design) com Java e Spring Boot

Este guia foi criado para servir como material de consulta rápida sempre que você for criar um projeto novo e quiser aplicar os conceitos de **DDD (Design Orientado ao Domínio)** na prática.

---

## 1. O que é o DDD de verdade?

A maioria dos sistemas começa pelo **Banco de Dados**: o programador cria as tabelas, gera classes cheias de `@Getter` e `@Setter` (sem nenhuma regra dentro) e joga toda a lógica em classes `Service` gigantescas de 1.000 linhas.

O **DDD** inverte essa lógica:
> **O centro do software não é o banco de dados nem o Spring Boot; é o Domínio (as regras do negócio da vida real).**

O DDD se divide em duas grandes áreas:
1. **Design Estratégico**: Como organizar e dividir um sistema grande sem virar uma bagunça.
2. **Design Tático**: Como escrever suas classes Java no dia a dia (`Entity`, `Value Object`, `Aggregate`, `Repository`, `Domain Service`, `Domain Event`).

---

## 2. Design Estratégico (Visão Macro)

### 2.1. Linguagem Ubíqua (Ubiquitous Language)
É o vocabulário único falado tanto pelas pessoas de negócio quanto pelo seu código Java.
- **PROIBIDO traduzir termos do negócio para "tecniquês"**:
  - Se na faculdade o processo se chama **"Trancar Matrícula"**, no seu código o método deve ser `matricula.trancar()`, e a rota `PATCH /matriculas/{id}/trancar`.
  - **Evite**: `matricula.setAtiva(false)` ou `matricula.setStatus(4)`.

| Como o negócio fala | Jeito Ruim (Tecniquês / CRUD) | Jeito DDD (Linguagem Ubíqua) |
| :--- | :--- | :--- |
| "Iniciar uma tarefa" | `tarefa.setStatus(Status.EM_ANDAMENTO)` | `tarefa.iniciar()` |
| "Estornar pagamento" | `pagamento.setFlagEstorno(true)` | `pagamento.estornar(motivo)` |
| "Bloquear cartão por perda" | `cartao.setSituacao(2)` | `cartao.bloquearPorPerda()` |

---

### 2.2. Contextos Delimitados (Bounded Contexts)
Um mesmo nome no mundo real tem significados diferentes dependendo do setor da empresa.

Imagine uma loja virtual e a palavra **`Produto`**:
- **No Catálogo / Vitrine**: Produto é `nome`, `descricao`, `fotos`, `avaliacoes`.
- **No Estoque / Logística**: Produto é `peso`, `altura`, `largura`, `corredorDoGalpao`, `quantidadeFisica`.
- **No Fiscal / Contabilidade**: Produto é `codigoNCM`, `aliquotaICMS`, `cfop`.

**O erro clássico**: Criar uma única classe `Produto.java` com 90 atributos tentando atender os 3 setores.
**A solução do DDD**: Dividir o projeto em **Contextos Delimitados** (seja em pacotes separados `br.com.loja.catalogo`, `br.com.loja.estoque` ou em microsserviços separados). Cada contexto tem sua própria classe focada apenas no que importa para ele!

---

## 3. Design Tático (Mão na Massa no Código Java)

### 3.1. Modelo Anêmico vs. Modelo Rico

#### Modelo Anêmico (O que evitar)
A entidade é só uma "bolsa de dados" sem cérebro. Qualquer classe de fora pode mudar os atributos para estados inválidos:

```java
// ENTIDADE ANÊMICA (Só tem dados, não protege suas regras)
@Data
@Entity
public class ContaBancaria {
    @Id
    private UUID id;
    private BigDecimal saldo;
    private boolean bloqueada;
}

// O Service acaba tendo que lembrar de todas as regras em todo lugar:
conta.setSaldo(conta.getSaldo().subtract(valor)); // E se a conta estiver bloqueada? E se não tiver saldo?
```

#### Modelo Rico (O padrão DDD)
A entidade **protege suas próprias invariantes** (regras que nunca podem ser quebradas). Você fecha os `setters` perigosos e expõe **métodos de negócio**:

```java
@Getter
@Entity
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal saldo = BigDecimal.ZERO;
    private boolean bloqueada = false;

    protected ContaBancaria() {} // Construtor exigido pelo JPA

    public void depositar(BigDecimal valor) {
        if (this.bloqueada) {
            throw new IllegalArgumentException("Conta bloqueada não pode receber depósitos");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo");
        }
        this.saldo = this.saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (this.bloqueada) {
            throw new IllegalArgumentException("Conta bloqueada não permite saques");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de saque inválido");
        }
        if (this.saldo.compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    public void bloquear() {
        this.bloqueada = true;
    }
}
```
> **Dica de Ouro com Lombok**: Em entidades ricas, prefira usar `@Getter` na classe em vez de `@Data` (ou evite usar `setStatus`/`setSaldo` fora da entidade), para ninguém burlar seus métodos de negócio!

---

### 3.2. Entidade vs. Objeto de Valor (Value Object)

Como saber se algo no seu projeto deve ser uma `@Entity` ou um **Value Object**?

| Característica | Entidade (`@Entity`) | Objeto de Valor (`@Embeddable` / `record`) |
| :--- | :--- | :--- |
| **Tem ID próprio?** | **Sim** (`UUID id`). | **Não** tem ID. |
| **O que define a igualdade?** | Dois objetos são iguais se tiverem o **mesmo ID**. | Dois objetos são iguais se tiverem os **mesmos valores**. |
| **É mutável?** | Muda de estado ao longo do tempo (ex: status muda). | **100% Imutável** (se mudar, você cria um novo). |
| **Exemplos** | `Usuario`, `Tarefa`, `Pedido`, `Aluno`. | `CPF`, `Email`, `Cep`, `Endereco`, `PeriodoDatas`. |

#### Exemplo de Objeto de Valor com JPA (`@Embeddable` + `@Embedded`)
Em vez de jogar `String cpf` solto e repetir validação de CPF em 10 telas diferentes, crie um Value Object:

```java
@Embeddable
public record Cpf(
    @Column(name = "cpf", nullable = false, length = 11)
    String numero
) {
    // Construtor compacto do record: valida no momento em que nasce!
    public Cpf {
        if (numero == null || !numero.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos numéricos");
        }
    }
}
```
E dentro da sua entidade `Cliente`:
```java
@Entity
public class Cliente {
    @Id
    private UUID id;

    @Embedded
    private Cpf cpf; // O JPA cria a coluna 'cpf' na tabela de cliente normalmente!
}
```

---

### 3.3. Agregados e Raiz de Agregação (Aggregate Root)

Um **Agregado** é um conjunto de objetos que formam uma unidade única no negócio (ex: `Tarefa` + `Subtarefa`, ou `Pedido` + `ItemPedido`).
A classe principal é chamada de **Raiz de Agregação (Aggregate Root)**.

#### As 3 Regras de Ouro de um Agregado:
1. **A Raiz é o único portão de entrada**: Nenhuma classe de fora altera uma entidade filha diretamente.
   - No seu projeto, para concluir uma `Subtarefa`, o `TarefaService` chama `tarefa.concluirSubtarefa(idSubtarefa)` (passando pela `Tarefa` pai!).
2. **A Raiz garante a consistência do todo**:
   - No seu projeto, o método `tarefa.concluir()` verifica se todas as `Subtarefa`s da lista estão concluídas antes de deixar a tarefa principal concluir.
3. **Só existe `Repository` para a Raiz de Agregação**:
   - Existe `TarefaRepository`, mas **não existe** `SubtarefaRepository`.
   - Existe `PedidoRepository`, mas **não existe** `ItemPedidoRepository`.
   - O JPA salva tudo junto usando `cascade = CascadeType.ALL, orphanRemoval = true`.

---

### 3.4. Onde fica cada coisa? (`Entity` vs `Domain Service` vs `Application Service`)

Uma dúvida muito comum é: *"Se as regras ficam na Entidade, para que serve a classe `@Service` do Spring?"*

No DDD, dividimos responsabilidades assim:

1. **Entidade (`Tarefa.java`, `Pedido.java`)**:
   - Guarda as regras que dependem **apenas dos dados dela mesma** e de seus filhos (ex: validar transição de status, calcular total dos itens, validar datas de início e fim).
2. **Application Service (`TarefaService.java`) — O "Maestro"**:
   - **Não faz conta nem regra de domínio pura**. Ele apenas **orquestra** o fluxo:
     1. Busca os objetos no `Repository`;
     2. Verifica permissões/existência no banco (ex: se o título já existe no banco ou se o usuário é dono);
     3. Chama o método de negócio da Entidade (`tarefa.concluir()`);
     4. Salva no `Repository` e devolve o `DTO`.
3. **Repository (`TarefaRepository.java`)**:
   - Cuida exclusivamente de buscar e guardar os Agregados no banco de dados.

---

## 4. Checklist Passo a Passo para Criar Seu Próximo Projeto com DDD

Sempre que for começar um projeto novo (ex: *Sistema de Biblioteca*, *Clínica Médica*, *Loja Virtual*), siga estes 5 passos na ordem:

1. **Liste as Ações do Negócio (Linguagem Ubíqua)** antes de pensar em telas ou tabelas:
   - *Ex (Biblioteca)*: `reservarLivro()`, `emprestar()`, `renovarEmprestimo()`, `devolver()`, `aplicarMultaPorAtraso()`.
2. **Identifique quem é Raiz de Agregação, quem é Entidade Filha e quem é Objeto de Valor**:
   - *Raiz de Agregação*: `Emprestimo`
   - *Entidade Filha*: `ItemEmprestimo` (controlado pelo `Emprestimo`)
   - *Objeto de Valor*: `PeriodoEmprestimo(LocalDate dataRetirada, LocalDate dataPrevistaDevolucao)`
3. **Programe os Métodos de Domínio dentro da Entidade (Modelo Rico)**:
   - Escreva os `if`s de proteção dentro de `emprestimo.renovar()` e `emprestimo.devolver()`, lançando exceções claras se alguém tentar quebrar uma regra (ex: *"Não é possível renovar um empréstimo que já está atrasado"*).
4. **Crie Repositórios apenas para as Raízes de Agregação**:
   - Use `cascade = CascadeType.ALL` para que a Raiz salve suas entidades filhas e seu `@ElementCollection` de histórico.
5. **Crie Rotas REST que refletem as Ações do Negócio (e não apenas `PUT` genérico)**:
   - Em vez de um único `PUT /emprestimos/{id}`, crie:
     - `PATCH /emprestimos/{id}/renovar`
     - `PATCH /emprestimos/{id}/devolver`
   - E use **HATEOAS** para mostrar no JSON apenas os links das ações que são válidas para o status atual!
