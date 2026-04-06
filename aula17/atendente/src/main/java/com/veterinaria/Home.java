package com.veterinaria;

import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class Home {
    public static Parent criarCena(String nomeAtendente){
        Image logo = new Image(Login.class.getResource("/imagens/pet.png").toExternalForm());
        ImageView mostarLogo = new ImageView(logo);
        mostarLogo.setFitHeight(60);
        mostarLogo.setPreserveRatio(true);

        Label lblTitulo = new Label("Tela de cadastro");
        lblTitulo.getStyleClass().add("titulo");

        Button btnDeslogar = new Button("Deslogar");

        HBox linhaHeader = new HBox(10);
        linhaHeader.getChildren().addAll(mostarLogo, lblTitulo, btnDeslogar);

        Label lblSubTitulo = new Label("Bem-Vindo(a) " + nomeAtendente + ", cadastre um novo cliente");
        lblSubTitulo.getStyleClass().add("subtitulo");

        HBox linhaSubTitulo = new HBox(10);
        linhaSubTitulo.getChildren().add(lblSubTitulo);

        Button btnCadastro = new Button("Cadastrar");

        HBox linhabtn = new HBox(50);
        linhabtn.getChildren().add(btnCadastro);

        Arrays.asList(linhaHeader, linhaSubTitulo, linhabtn).forEach(linha -> linha.setAlignment(Pos.CENTER));

        VBox verticalHome = new VBox(50);
        verticalHome.getChildren().addAll(linhaHeader, linhaSubTitulo, linhabtn);
        verticalHome.setAlignment(Pos.CENTER);

        btnCadastro.setOnAction(evento -> {
            App.inserirCena(Cadastro.criarCena(nomeAtendente));
        });

    
         btnDeslogar.setOnAction(evento -> {
            App.inserirCena(Login.criarCena());
        });

        return verticalHome;

    }
}
