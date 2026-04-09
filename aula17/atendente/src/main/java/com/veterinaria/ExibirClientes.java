package com.veterinaria;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ExibirClientes {
    public static Parent criarCena(){
        Accordion sanfonaClientes = new Accordion();
        sanfonaClientes.setMaxWidth(500);

    // 2. Passa por cada cliente no banco de dados
    for (Cliente cliente : BancoDeDados.getClientes()) {
        
        // 3. Cria a "caixa" (VBox) que vai ficar ESCONDIDA dentro da sanfona
        VBox detalhes = new VBox(5); 
        
        // Adiciona as infos do dono
        detalhes.getChildren().add(new Label(cliente.exibeDados()));
         // Ajuste conforme seus métodos
        
        // Adiciona um título pros Pets
        Label tituloPets = new Label("\n🐾 Pets do Cliente:");
        tituloPets.setStyle("-fx-font-weight: bold;"); // Deixa negrito
        detalhes.getChildren().add(tituloPets);
        
        // Lista os pets dentro dessa mesma caixa
        if (cliente.getPet().isEmpty()) {
            detalhes.getChildren().add(new Label("Nenhum pet cadastrado."));
        } else {
            for (Pet p : cliente.getPet()) {
                detalhes.getChildren().add(new Label("\nPet "+ (cliente.getPet().indexOf(p) + 1) + ":\n" +p.exibePet()));
            }
        }

        // 4. Cria a "Barra" (TitledPane) que fica visível, colocando a VBox dentro dela!
        TitledPane painelCliente = new TitledPane(cliente.getNome(), detalhes);
        painelCliente.setMaxHeight(25);

        
        // 5. Adiciona essa barra na sanfona principal
        sanfonaClientes.getPanes().add(painelCliente);

        

        
    }
    ScrollPane rolagem = new ScrollPane();
        rolagem.setContent(sanfonaClientes);
        rolagem.setFitToWidth(true);
         rolagem.setMaxWidth(500);
    VBox coluna = new VBox(25);
        coluna.getChildren().addAll(rolagem);
        coluna.setPadding(new Insets(30, 20, 20, 20));
        coluna.setAlignment(Pos.CENTER);
        VBox.setVgrow(rolagem,  Priority.ALWAYS);
        VBox.setVgrow(sanfonaClientes,  Priority.ALWAYS);
        return coluna;
    }
}
