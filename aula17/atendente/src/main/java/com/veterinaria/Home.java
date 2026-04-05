package com.veterinaria;

import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class Home {
    public static Scene criarCena(String nomeAtendente){
        Image logo = new Image(Login.class.getResource("/imagens/pet.png").toExternalForm());
        ImageView mostarLogo = new ImageView(logo);
        mostarLogo.setFitHeight(60);
        mostarLogo.setPreserveRatio(true);

        Label lblTitulo = new Label("Tela de cadastro");
        lblTitulo.getStyleClass().add("titulo");

        HBox linhaHeader = new HBox(10);
        linhaHeader.getChildren().addAll(mostarLogo, lblTitulo);

        Label lblSubTitulo = new Label("Bem-Vindo(a) " + nomeAtendente + ", escolha o tipo de cadastro");
        lblSubTitulo.getStyleClass().add("subtitulo");

        HBox linhaSubTitulo = new HBox(10);
        linhaSubTitulo.getChildren().add(lblSubTitulo);

        Button btnCliente = new Button("Cliente");
        Button btnPet = new Button("Pet");

        HBox linhabtn = new HBox(50);
        linhabtn.getChildren().addAll(btnCliente, btnPet);

        Arrays.asList(linhaHeader, linhaSubTitulo, linhabtn).forEach(linha -> linha.setAlignment(Pos.CENTER));

        VBox verticalHome = new VBox(50);
        verticalHome.getChildren().addAll(linhaHeader, linhaSubTitulo, linhabtn);
        verticalHome.setAlignment(Pos.CENTER);

        btnPet.setOnAction(evento -> {
            App.inserirCena(Pet.criarCena());
        });

        return new Scene(verticalHome, 1000, 600);

    }
}
