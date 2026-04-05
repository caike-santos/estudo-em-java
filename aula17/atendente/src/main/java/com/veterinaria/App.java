package com.veterinaria;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage palcoPrincipal;

    @Override
    public void start(Stage stage) {
        
        palcoPrincipal = stage;
        palcoPrincipal.setTitle("Pagina do Atendente");
        inserirCena(Login.criarCena());

       palcoPrincipal.show();
    }

    public static void inserirCena(Scene cenaAtual){
        String cssLogin = App.class.getResource("/styles/style.css").toExternalForm();
        cenaAtual.getStylesheets().add(cssLogin);
        palcoPrincipal.setScene(cenaAtual);
    }

    public static void main(String[] args) {
        launch();
    }

}