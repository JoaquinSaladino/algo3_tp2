package edu.fiuba.algo3.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class FinDePartidaControlador {

    @FXML private Label lblParte1;
    @FXML private Label lblParte2;
    @FXML private Label lblParte3;

    @FXML
    public void initialize()
    {
        //setearVictoriaMafia();
        setearVictoriaPueblo();
    }

    public void setearVictoriaMafia()
    {
        lblParte1.setText("¡LA ");

        lblParte2.setText("MAFIA");
        lblParte2.setTextFill(Color.web("#cc0000"));

        lblParte3.setText(" DOMINA LA CIUDAD!");
    }

    public void setearVictoriaPueblo()
    {
        lblParte1.setText("¡EL ");

        lblParte2.setText("PUEBLO");
        lblParte2.setTextFill(Color.WHITE);

        lblParte3.setText(" HA TRIUNFADO!");
    }

    @FXML
    public void manejarBotonRevelar(ActionEvent event) {
        System.out.println("Yendo al carrusel de revelación de jugadores...");

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaRevelacion.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 1000, 600));
        }

        catch (IOException e)
        {
            System.err.println("Error al cargar la pantalla de revelación.");
            e.printStackTrace();
        }
    }

    @FXML
    public void manejarBotonSalir(ActionEvent event)
    {
        Platform.exit();
    }
}