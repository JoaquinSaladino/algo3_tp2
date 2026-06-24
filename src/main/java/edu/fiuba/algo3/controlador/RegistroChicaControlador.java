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

public class RegistroChicaControlador {

    @FXML private TextField nombreJugador1;
    @FXML private TextField nombreJugador2;
    @FXML private TextField nombreJugador3;
    @FXML private TextField nombreJugador4;
    @FXML private TextField nombreJugador5;

    @FXML private Button botonAgregarSexto;
    @FXML private VBox cajaSextoJugador;
    @FXML private TextField nombreJugador6;

    @FXML private Button botonJugar;

    @FXML
    public void manejarAgregarSextoJugador(ActionEvent event) {
        botonAgregarSexto.setVisible(false);
        cajaSextoJugador.setVisible(true);
    }

    @FXML
    public void manejarBotonJugar(ActionEvent event) {
        List<String> nombres = new ArrayList<>();

        agregarSiEsValido(nombres, nombreJugador1);
        agregarSiEsValido(nombres, nombreJugador2);
        agregarSiEsValido(nombres, nombreJugador3);
        agregarSiEsValido(nombres, nombreJugador4);
        agregarSiEsValido(nombres, nombreJugador5);

        if (cajaSextoJugador.isVisible())
        {
            agregarSiEsValido(nombres, nombreJugador6);
        }

        if (nombres.size() < 5)
        {
            System.out.println("Error: Se necesitan al menos 5 jugadores para iniciar.");
            return;
        }

        System.out.println("Nombres recolectados para la partida: " + nombres);

        Juego juego = App.getJuego();
        juego.configurarPartida(nombres);
        juego.iniciarPartida();

        try
        {
            System.out.println("Juego registro: " + juego);
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