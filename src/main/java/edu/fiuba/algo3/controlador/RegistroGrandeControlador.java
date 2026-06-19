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
import java.util.ArrayList;
import java.util.List;

public class RegistroGrandeControlador {

    @FXML private TextField nombreJugador1;
    @FXML private TextField nombreJugador2;
    @FXML private TextField nombreJugador3;
    @FXML private TextField nombreJugador4;
    @FXML private TextField nombreJugador5;
    @FXML private TextField nombreJugador6;
    @FXML private TextField nombreJugador7;
    @FXML private TextField nombreJugador8;
    @FXML private TextField nombreJugador9;
    @FXML private TextField nombreJugador10;

    @FXML private Button botonAgregarOnce;
    @FXML private VBox cajaOnceJugador;
    @FXML private TextField nombreJugador11;

    @FXML private Button botonAgregarDoce;
    @FXML private VBox cajaDoceJugador;
    @FXML private TextField nombreJugador12;

    @FXML
    public void manejarAgregarOnceJugador(ActionEvent event) {
        botonAgregarOnce.setVisible(false);
        cajaOnceJugador.setVisible(true);
    }

    @FXML
    public void manejarAgregarDoceJugador(ActionEvent event) {
        botonAgregarDoce.setVisible(false);
        cajaDoceJugador.setVisible(true);
    }

    @FXML
    public void manejarBotonJugar(ActionEvent event) {
        List<String> nombres = new ArrayList<>();

        agregarSiEsValido(nombres, nombreJugador1);
        agregarSiEsValido(nombres, nombreJugador2);
        agregarSiEsValido(nombres, nombreJugador3);
        agregarSiEsValido(nombres, nombreJugador4);
        agregarSiEsValido(nombres, nombreJugador5);
        agregarSiEsValido(nombres, nombreJugador6);
        agregarSiEsValido(nombres, nombreJugador7);
        agregarSiEsValido(nombres, nombreJugador8);
        agregarSiEsValido(nombres, nombreJugador9);
        agregarSiEsValido(nombres, nombreJugador10);

        if (cajaOnceJugador.isVisible())
        {
            agregarSiEsValido(nombres, nombreJugador11);
        }

        if (cajaDoceJugador.isVisible())
        {
            agregarSiEsValido(nombres, nombreJugador12);
        }

        if (nombres.size() < 10)
        {
            System.out.println("Error: Se necesitan al menos 10 jugadores para iniciar.");
            return;
        }

        System.out.println("Nombres recolectados para la partida: " + nombres);
    }

    private void agregarSiEsValido(List<String> lista, TextField campo)
    {
        String texto = campo.getText();

        if (texto != null && !texto.trim().isEmpty())
        {
            lista.add(texto.trim());
        }
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