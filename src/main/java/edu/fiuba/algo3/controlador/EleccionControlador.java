package edu.fiuba.algo3.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class EleccionControlador {

    @FXML
    private Button botonVolver;

    @FXML
    public void manejarPartidaChica(ActionEvent event)
    {
        try
        {
            System.out.println("Configurando Partida Chica...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaRegistroChica.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void manejarPartidaMediana(ActionEvent event)
    {
        try
        {
            System.out.println("Configurando Partida Mediana...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaRegistroMediana.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void manejarPartidaGrande(ActionEvent event)
    {
        try
        {
            System.out.println("Configurando Partida Grande...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaRegistroGrande.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void manejarBotonVolver(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaInicio.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botonVolver.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void manejarBotonSalir(ActionEvent event)
    {
        Platform.exit();
    }
}