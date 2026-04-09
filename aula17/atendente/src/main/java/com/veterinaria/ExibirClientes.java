package com.veterinaria;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ExibirClientes {
    public static Parent criarCena(){
        TextArea listaClientes = new TextArea();
        listaClientes.setMaxWidth(500);
        listaClientes.appendText(BancoDeDados.exibirClientes());

        HBox lista = new HBox();
        lista.getChildren().add(listaClientes);
        lista.setAlignment(Pos.CENTER);

        Button voltar = new Button("Voltar");
        HBox btn = new HBox();
        btn.getChildren().add(voltar);
        btn.setAlignment(Pos.CENTER);

        VBox coluna = new VBox();
        coluna.getChildren().addAll(lista, voltar);
        coluna.setPadding(new Insets(30, 20, 20, 20));
        VBox.setVgrow(lista, Priority.ALWAYS);

        voltar.setOnAction(evento -> {
            App.inserirCena(Home.criarCena(Login.getAtendente()));
        });
       
        return coluna;
    }
}
