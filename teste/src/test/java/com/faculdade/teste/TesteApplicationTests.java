package com.faculdade.teste;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.faculdade.teste.dto.*;
import com.faculdade.teste.model.Prioridade;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TesteApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Guarda o token e IDs compartilhados entre os testes da suíte
    private static String token;
    private static String idUsuario;
    private static String idTarefa;
    private static String idSubtarefa;

    @Test
    @Order(1)
    @DisplayName("1. Deve cadastrar um novo usuário com sucesso (POST /usuarios)")
    void deveCadastrarUsuario() throws Exception {
        UsuarioRequestDto dto = new UsuarioRequestDto(
                "usuario_teste",
                "senha123456",
                LocalDate.of(2000, 1, 1)
        );

        MvcResult result = mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("usuario_teste"))
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        idUsuario = jsonNode.get("id").asText();
    }

    @Test
    @Order(2)
    @DisplayName("2. Deve realizar login e gerar token JWT (POST /usuarios/login)")
    void deveLogarEObterToken() throws Exception {
        LoginRequestDto loginDto = new LoginRequestDto("usuario_teste", "senha123456");

        MvcResult result = mockMvc.perform(post("/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        token = jsonNode.get("token").asText();
    }

    @Test
    @Order(3)
    @DisplayName("3. Deve criar uma tarefa com token JWT no cabeçalho (POST /tarefas)")
    void deveCriarTarefaComToken() throws Exception {
        TarefaRequestDto dto = new TarefaRequestDto(
                "Minha Primeira Tarefa",
                "Descrição detalhada da tarefa de teste",
                Prioridade.ALTA,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2)
        );

        MvcResult result = mockMvc.perform(post("/tarefas")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Minha Primeira Tarefa"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$.status").value("PENDENTE"))
                .andExpect(jsonPath("$._links.iniciar.href").exists())
                .andExpect(jsonPath("$._links.concluir.href").exists())
                .andExpect(jsonPath("$._links.cancelar.href").exists())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        idTarefa = jsonNode.get("id").asText();
    }

    @Test
    @Order(4)
    @DisplayName("4. Deve listar as tarefas do usuário autenticado (GET /tarefas)")
    void deveListarTarefasComToken() throws Exception {
        mockMvc.perform(get("/tarefas")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.tarefaResponseDtoList").isArray())
                .andExpect(jsonPath("$._links.self.href").exists());
    }

    @Test
    @Order(5)
    @DisplayName("5. Deve buscar uma tarefa por ID com links HATEOAS (GET /tarefas/{id})")
    void deveBuscarTarefaPorId() throws Exception {
        mockMvc.perform(get("/tarefas/" + idTarefa)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idTarefa))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.atualizar.href").exists())
                .andExpect(jsonPath("$._links.deletar.href").exists());
    }

    @Test
    @Order(6)
    @DisplayName("6. Deve atualizar uma tarefa completamente (PUT /tarefas/{id})")
    void deveAtualizarTarefaCompleta() throws Exception {
        TarefaRequestDto dto = new TarefaRequestDto(
                "Tarefa Atualizada Completa",
                "Nova descrição da tarefa",
                Prioridade.URGENTE,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(3)
        );

        mockMvc.perform(put("/tarefas/" + idTarefa)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Tarefa Atualizada Completa"));
    }

    @Test
    @Order(7)
    @DisplayName("7. Deve atualizar uma tarefa parcialmente (PATCH /tarefas/{id})")
    void deveAtualizarTarefaParcial() throws Exception {
        TarefaPatchDto patchDto = new TarefaPatchDto(
                "Tarefa Título Patch",
                null,
                null,
                null,
                null
        );

        mockMvc.perform(patch("/tarefas/" + idTarefa)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Tarefa Título Patch"));
    }

    @Test
    @Order(8)
    @DisplayName("8. Deve bloquear requisição a tarefas sem o cabeçalho Authorization")
    void deveBloquearSemToken() throws Exception {
        mockMvc.perform(get("/tarefas"))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(9)
    @DisplayName("9. cadastrar subtarefa")
    void cadastrarSubtarefa() throws Exception{
        SubtarefaRequestDto subtarefaRequestDto = new SubtarefaRequestDto("minha subtarefa");

        MvcResult result = mockMvc.perform(post("/tarefas/" + idTarefa +"/subtarefas")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subtarefaRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subtarefas").isArray())
                .andExpect(jsonPath("$.subtarefas[0].id").exists())
                .andExpect(jsonPath("$.subtarefas[0].titulo").value("minha subtarefa"))
                .andExpect(jsonPath("$.subtarefas[0].concluida").value(false))
                .andReturn();
        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        idSubtarefa = jsonNode.get("subtarefas").get(0).get("id").asText();
    }

    @Test
    @Order(10)
    @DisplayName("10. concluir subtarefa")
    void concluirSubtarefa() throws Exception{
        mockMvc.perform(patch("/tarefas/" + idTarefa +"/subtarefas/" + idSubtarefa + "/concluir")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subtarefas[0].concluida").value(true));
    }

    @Test
    @Order(11)
    @DisplayName("11. Deve iniciar uma tarefa e mostrar links HATEOAS (concluir e cancelar)")
    void deveIniciarUmaTarefa() throws Exception {
        mockMvc.perform(patch("/tarefas/" + idTarefa + "/iniciar")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_ANDAMENTO"))
                .andExpect(jsonPath("$._links.concluir.href").exists())
                .andExpect(jsonPath("$._links.cancelar.href").exists());
    }

    @Test
    @Order(12)
    @DisplayName("12. Deve concluir uma tarefa e mostrar a data de conclusão")
    void deveConcluirUmaTarefa() throws Exception {
        mockMvc.perform(patch("/tarefas/" + idTarefa + "/concluir")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.conclusao").exists())
                .andExpect(jsonPath("$.status").value("CONCLUIDA"));
    }

    @Test
    @Order(13)
    @DisplayName("13. Deve dar erro ao cancelar uma tarefa já concluída")
    void cancelarTarefaConcluida() throws Exception{
        mockMvc.perform(patch("/tarefas/" + idTarefa + "/cancelar")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(14)
    @DisplayName("14. Deve deletar uma tarefa com sucesso (DELETE /tarefas/{id})")
    void deveDeletarTarefa() throws Exception {
        mockMvc.perform(delete("/tarefas/" + idTarefa)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(15)
    @DisplayName("15. Deve cancelar uma tarefa")
    void cancelarTarefa() throws Exception{
        TarefaRequestDto dto = new TarefaRequestDto(
                "Minha Segunda Tarefa",
                "Descrição detalhada da tarefa de teste",
                Prioridade.ALTA,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2)
        );

        MvcResult result = mockMvc.perform(post("/tarefas")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        String idTarefaNova = jsonNode.get("id").asText();

        mockMvc.perform(patch("/tarefas/" + idTarefaNova + "/cancelar")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELADA"));
    }
}
