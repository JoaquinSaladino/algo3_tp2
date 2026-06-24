package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Juego;
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

    @FXML private Button botonJugar;

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
    public void manejarBotonJugar(ActionEvent event) {
        List<String> nombres = new ArrayList<>();

        agregarSiEsValido(nombres, nombreJugador1);
        agregarSiEsValido(nombres, nombreJugador2);
        agregarSiEsValido(nombres, nombreJugador3);
        agregarSiEsValido(nombres, nombreJugador4);
        agregarSiEsValido(nombres, nombreJugador5);
        agregarSiEsValido(nombres, nombreJugador6);
        agregarSiEsValido(nombres, nombreJugador7);

        if (cajaOctavoJugador.isVisible())
        {
            agregarSiEsValido(nombres, nombreJugador8);
        }

        if (cajaNovenoJugador.isVisible())
        {
            agregarSiEsValido(nombres, nombreJugador9);
        }

        if (nombres.size() < 7)
        {
            System.out.println("Error: Se necesitan al menos 7 jugadores para iniciar.");
            return;
        }

        System.out.println("Nombres recolectados para la partida: " + nombres);

        Juego juego = App.getJuego();
        juego.configurarPartida(nombres);
        juego.iniciarPartida();

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaFaseNocturna.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) botonJugar.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
        }

        catch (IOException e)
        {
            System.err.println("Error al cambiar a la pantalla de Fase Nocturna.");
            e.printStackTrace();
        }
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