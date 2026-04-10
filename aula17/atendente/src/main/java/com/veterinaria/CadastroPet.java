package com.veterinaria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CadastroPet {
    private static TextField txtNome;
    private static TextField txtIdade;
    private static TextField txtRaca;
    private static  ComboBox<String> cbmSexo;
    private static List<Pet> listaPet = new ArrayList<>();
    private static TextArea cadastro;
    private static VBox layout;
    public static Parent criarCena(){
        //header
        

        Image logo = new Image(CadastroPet.class.getResource("/imagens/pet.png").toExternalForm());
        ImageView visulogoCadastro = new ImageView(logo);
        visulogoCadastro.setFitHeight(60);
        visulogoCadastro.setPreserveRatio(true);

        Label lblTituloCadastro = new Label("Cadastro do Pet");
        lblTituloCadastro.getStyleClass().add("titulo");

        

        HBox header = new HBox(20);
        header.getChildren().addAll(visulogoCadastro, lblTituloCadastro);
        header.getStyleClass().add("header");
        //fimHeader

        //formulario
        GridPane gridPet = new GridPane();
        gridPet.setAlignment(Pos.CENTER);
        gridPet.setHgap(10);
        gridPet.setVgap(15);


        Label lblNome = new Label("Nome do pet:");
        txtNome = new TextField();
        txtNome.setMaxWidth(250);
        
        Label lblIdade = new Label("Idade do pet:");
        txtIdade = new TextField();
        txtIdade.setMaxWidth(250);
        txtIdade.textProperty().addListener((observable, valorAntigo, valorNovo) -> {

        if (!valorNovo.matches("\\d*")) {
            txtIdade.setText(valorNovo.replaceAll("[^\\d]", ""));
        }
         });

        Label lblRaca = new Label("Raça do pet:");
        txtRaca = new TextField();
        txtRaca.setMaxWidth(250);

        Label lblSexo = new Label("Sexo do pet:");
        cbmSexo = new ComboBox<>();
        cbmSexo.getItems().addAll("Macho", "Femea");
        cbmSexo.getSelectionModel().selectFirst();

        gridPet.add(lblNome, 0, 0);
        gridPet.add(txtNome, 1, 0);

        gridPet.add(lblIdade, 0, 1);
        gridPet.add(txtIdade, 1, 1);

        gridPet.add(lblRaca, 0, 2);
        gridPet.add(txtRaca, 1, 2);

        gridPet.add(lblSexo, 0, 3);
        gridPet.add(cbmSexo, 1, 3);

        Button btnCadastrar = new Button("Cadastrar Pet");

        cadastro = new TextArea();
        cadastro.setEditable(false);
        cadastro.setMaxWidth(300);
        cadastro.setPrefHeight(150);

        btnCadastrar.setOnAction(evento -> 
            {
                String nome = txtNome.getText();
                String idade = txtIdade.getText();
                String raca = txtRaca.getText();
                String sexo = cbmSexo.getValue();

                if(nome.isEmpty() || idade.isEmpty() || raca.isEmpty() || sexo.isEmpty()){
                    Alert aviso = new Alert(Alert.AlertType.WARNING);
                    aviso.setTitle("Erro");
                    aviso.setHeaderText("Erro de Preenchimento");
                    aviso.setContentText("Preencha todos os campos");
                    aviso.showAndWait();
                }else{
                    if(!idade.matches("\\d*")){

                        Alert avisonum = new Alert(Alert.AlertType.WARNING);
                        
                        avisonum.setTitle("Erro");
                        avisonum.setHeaderText("Erro de Preenchimento");
                        avisonum.setContentText("Preencha o campo idade com numeros");
                        avisonum.showAndWait();
                    }else{

                         Pet pet = CadastroPet.dadosPet();
                         listaPet.add(pet);

                        System.out.println("nome: "+ pet.getNome());
                        System.out.println("idade: "+ pet.getIdade());
                        System.out.println("raca: "+ pet.getRaca());
                        System.out.println("sexo: "+ pet.getSexo());

                        cadastro.appendText("\nCadastro concluido...\n");
                        cadastro.appendText("nome: "+ pet.getNome()+"\n");
                        cadastro.appendText("idade: "+ pet.getIdade()+"\n");
                        cadastro.appendText("raca: "+ pet.getRaca()+"\n");
                        cadastro.appendText("sexo: "+ pet.getSexo()+"\n");

                        txtIdade.clear();
                        txtNome.clear();
                        txtRaca.clear();
                }
                }
                
        });
        //fimCadastro
        

        //montandoCena
        //HBox linhaNome = new HBox(10);
        //HBox linhaIdade = new HBox(10);
        //HBox linhaRaca = new HBox(10);
        //HBox linhaSexo = new HBox(10); 
        HBox linhaBtn = new HBox();
        HBox linhaCadastro = new HBox();

        //linhaNome.getChildren().addAll(lblNome, txtNome);
        //linhaIdade.getChildren().addAll(lblIdade, txtIdade);
        //linhaRaca.getChildren().addAll(lblRaca, txtRaca);
        //linhaSexo.getChildren().addAll(lblSexo, cbmSexo);
        linhaBtn.getChildren().add(btnCadastrar);
        linhaCadastro.getChildren().add(cadastro);
       

        layout = new VBox(20);

        Arrays.asList(header, linhaBtn, linhaCadastro)
      .forEach(linha -> linha.setAlignment(Pos.CENTER));
        
        layout.setAlignment(Pos.CENTER); 

        layout.getChildren().addAll(header, gridPet, linhaBtn, linhaCadastro);
        

        //VBox.setVgrow(linhaCadastro, Priority.ALWAYS);
        //HBox.setHgrow(linhaNome, Priority.ALWAYS);

        //vertical.setMaxWidth(2000);
         return layout;
         
        //fimMontandoCena
        //fimCadastro
    }

    public static Pet dadosPet(){
        String nome = txtNome.getText();
        int idade = Integer.parseInt(txtIdade.getText());
        String raca = txtRaca.getText();
        String sexo = cbmSexo.getValue();

        return new Pet(nome, idade, sexo, raca);

    }

    public static TextArea getCadastro() {
        return cadastro;
    }
    public static ComboBox<String> getCbmSexo() {
        return cbmSexo;
    }
    public static TextField getTxtIdade() {
        return txtIdade;
    }
    public static TextField getTxtNome() {
        return txtNome;
    }
    public static TextField getTxtRaca() {
        return txtRaca;
    }
    public static List<Pet> getListaPet() {
        return listaPet;
    }
    public static VBox getLayout() {
        return layout;
    }
}
