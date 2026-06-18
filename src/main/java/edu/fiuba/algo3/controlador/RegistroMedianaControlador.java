package edu.fiuba.algo3.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroMedianaControlador {

    @FXML private TextField nombreJugador1;
    @FXML private TextField nombreJugador2;
    @FXML private TextField nombreJugador3;
    @FXML private TextField nombreJugador4;
    @FXML private TextField nombreJugador5;
    @FXML private TextField nombreJugador6;
    @FXML private TextField nombreJugador7;

    @FXML private Button botonAgregarOctavo;
    @FXML private VBox cajaOctavoJugador;
    @FXML private TextField nombreJugador8;

    @FXML private Button botonAgregarNoveno;
    @FXML private VBox cajaNovenoJugador;
    @FXML private TextField nombreJugador9;

    @FXML
    public void manejarAgregarOctavoJugador(ActionEvent event) {
        botonAgregarOctavo.setVisible(false);
        cajaOctavoJugador.setVisible(true);
    }

    @FXML
    public void manejarAgregarNovenoJugador(ActionEvent event) {
        botonAgregarNoveno.setVisible(false);
        cajaNovenoJugador.setVisible(true);
    }

    @FXML
    public void manejarBotonJugar(ActionEvent event)
    {
        // Recolectar nombres y dar play
    }

    @FXML
    public void manejarBotonVolver(ActionEvent event) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaEleccion.fxml"));
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
    public void manejarBotonSalir(ActionEvent event)
    {
        Platform.exit();
    }
}