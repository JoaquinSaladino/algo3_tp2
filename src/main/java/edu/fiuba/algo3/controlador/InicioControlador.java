package edu.fiuba.algo3.controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioControlador {

    @FXML
    private Button botonJugar;

    @FXML
    private Button botonSalir;

    @FXML
    public void manejarBotonJugar()
    {
        try
        {
            System.out.println("Cambio de escena a eleccion de tipo de partida...");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaEleccion.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) botonJugar.getScene().getWindow();

            stage.setScene(new Scene(root, 1000, 600));

        }

        catch (IOException e)
        {
            System.err.println("Error al cargar la pantalla de eleccion.");
            e.printStackTrace();
        }
    }

    @FXML
    public void manejarBotonSalir()
    {
        Platform.exit();
    }
}