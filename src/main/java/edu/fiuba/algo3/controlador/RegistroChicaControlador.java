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

public class RegistroChicaControlador {

    @FXML private TextField nombreJugador1;
    @FXML private TextField nombreJugador2;
    @FXML private TextField nombreJugador3;
    @FXML private TextField nombreJugador4;
    @FXML private TextField nombreJugador5;

    @FXML private Button botonAgregarSexto;
    @FXML private VBox cajaSextoJugador;
    @FXML private TextField nombreJugador6;

    @FXML
    public void manejarAgregarSextoJugador(ActionEvent event) {
        botonAgregarSexto.setVisible(false);
        cajaSextoJugador.setVisible(true);
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
            Stage stage = (Stage) botonAgregarSexto.getScene().getWindow();
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